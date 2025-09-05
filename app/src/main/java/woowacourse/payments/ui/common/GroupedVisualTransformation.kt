package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class GroupedVisualTransformation(
    private val groupSize: Int,
    private val separator: String,
) : VisualTransformation {
    init {
        require(groupSize > MIN_GROUP_SIZE) { ERROR_INVALID_MIN_GROUP_SIZE }
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val formattedText = buildFormattedText(originalText)
        val offsetMapping =
            GroupedOffsetMapping(
                groupSize = groupSize,
                originalLength = originalText.length,
                transformedLength = formattedText.length,
                separatorLength = separator.length,
            )
        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }

    private fun buildFormattedText(input: String): String {
        if (input.isEmpty()) return ""
        return input.chunked(groupSize).joinToString(separator)
    }

    private class GroupedOffsetMapping(
        private val groupSize: Int,
        private val originalLength: Int,
        private val transformedLength: Int,
        private val separatorLength: Int,
    ) : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= MIN_OFFSET) return MIN_OFFSET
            val groupsBefore = offset / groupSize
            return (offset + groupsBefore * separatorLength).coerceAtMost(transformedLength)
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= MIN_OFFSET) return MIN_OFFSET
            val groupWithSep = groupSize + separatorLength
            val groups = offset / groupWithSep
            val posInGroup = offset % groupWithSep
            val originalPos = groups * groupSize + posInGroup
            return originalPos.coerceAtMost(originalLength)
        }
    }

    companion object {
        private const val MIN_OFFSET = 0
        private const val MIN_GROUP_SIZE = 0
        private const val ERROR_INVALID_MIN_GROUP_SIZE = "groupSize는 ${MIN_GROUP_SIZE}보다 커야 합니다."
    }
}

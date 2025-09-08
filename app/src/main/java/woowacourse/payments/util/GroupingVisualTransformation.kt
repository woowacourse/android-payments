package woowacourse.payments.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class GroupingVisualTransformation(
    private val groupSize: Int,
    private val separator: String,
) : VisualTransformation {
    init {
        require(groupSize > 0) { "groupSize $groupSize must be greater than zero." }
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText: String = text.chunked(groupSize).joinToString(separator)

        val offsetTranslator: OffsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val separatorCount = (offset - 1).coerceAtLeast(0) / groupSize
                    return offset + separatorCount * separator.length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val separatorCount = offset / (groupSize + separator.length)
                    return offset - separatorCount * separator.length
                }
            }

        return TransformedText(AnnotatedString(transformedText), offsetTranslator)
    }
}

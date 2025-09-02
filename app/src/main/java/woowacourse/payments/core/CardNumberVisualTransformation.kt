package woowacourse.payments.core

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation(
    private val maxLength: Int,
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val newText = buildString {
            text.forEachIndexed { index, char ->
                append(char)

                val indexInGroup = index % GROUP_SIZE
                val lastIndexOfGroup = GROUP_SIZE - 1
                val isLastOfGroup = indexInGroup == lastIndexOfGroup
                val isNotLastIndex = index != maxLength - 1

                if (isLastOfGroup && isNotLastIndex) append(SEPARATOR)
            }
        }

        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = when {
                offset <= 3 -> offset
                offset <= 7 -> offset + 3
                offset <= 11 -> offset + 6
                offset <= 15 -> offset + 9
                else -> 19 + 6
            }

            override fun transformedToOriginal(offset: Int): Int = when {
                offset <= 3 -> offset
                offset <= 10 -> offset - 3
                offset <= 17 -> offset - 6
                offset <= 24 -> offset - 9
                else -> maxLength
            }
        }

        return TransformedText(AnnotatedString(newText), offsetTranslator)
    }

    companion object {
        private const val GROUP_SIZE = 4
        private const val SEPARATOR = " - "
    }
}

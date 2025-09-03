package woowacourse.payments.core

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpireDateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val newText = buildString {
            text.forEachIndexed { index, char ->
                append(char)

                val isMonthInputComplete = index == 1
                val shouldInsertSeparator = text.length > GROUP_SIZE
                if (isMonthInputComplete && shouldInsertSeparator) append(SEPARATOR)
            }
        }

        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (text.length <= GROUP_SIZE) return offset

                return when {
                    offset <= 3 -> offset + SEPARATOR.length
                    else -> newText.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (text.length <= GROUP_SIZE) return offset

                return when {
                    offset <= GROUP_SIZE + SEPARATOR.length -> GROUP_SIZE
                    offset <= newText.length -> offset - SEPARATOR.length
                    else -> text.length
                }
            }
        }

        return TransformedText(AnnotatedString(newText), offsetTranslator)
    }

    companion object {
        private const val GROUP_SIZE = 2
        private const val SEPARATOR = " / "
    }
}

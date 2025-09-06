package woowacourse.payments.core

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpireDateVisualTransformation(
    private val groupSize: Int,
    private val separator: String
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val newText = buildString {
            text.forEachIndexed { index, char ->
                append(char)

                val isMonthInputComplete = index == 1
                val shouldInsertSeparator = text.length > groupSize
                if (isMonthInputComplete && shouldInsertSeparator) append(separator)
            }
        }

        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (text.length <= groupSize) return offset

                return when {
                    offset <= 3 -> offset + separator.length
                    else -> newText.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (text.length <= groupSize) return offset

                return when {
                    offset <= groupSize + separator.length -> groupSize
                    offset <= newText.length -> offset - separator.length
                    else -> text.length
                }
            }
        }

        return TransformedText(AnnotatedString(newText), offsetTranslator)
    }
}

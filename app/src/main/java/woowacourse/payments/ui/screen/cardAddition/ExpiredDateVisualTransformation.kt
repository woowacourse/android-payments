package woowacourse.payments.ui.screen.cardAddition

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpiredDateVisualTransformation(
    private val maxInputLength: Int,
    private val delimiter: String = EXPIRED_DATE_DELIMITER,
) : VisualTransformation {
    private val offsetMapping =
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                return offset + delimiter.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                return offset - delimiter.length
            }
        }

    override fun filter(text: AnnotatedString): TransformedText {
        val rawInput = text.text.take(maxInputLength)
        val formattedText =
            buildString {
                rawInput.forEachIndexed { index: Int, char: Char ->
                    append(char)
                    if (index == 1) append(delimiter)
                }
            }
        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }

    companion object {
        private const val EXPIRED_DATE_DELIMITER: String = " / "
    }
}

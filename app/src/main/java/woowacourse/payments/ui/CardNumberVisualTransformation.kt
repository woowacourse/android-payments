package woowacourse.payments.ui

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation(
    private val maxInputLength: Int,
    private val delimiter: String = CARD_NUMBER_DELIMITER,
) : VisualTransformation {
    private val offsetMapping =
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int =
                when {
                    offset <= 3 -> offset
                    offset <= 7 -> offset + delimiter.length * 1
                    offset <= 11 -> offset + delimiter.length * 2
                    else -> offset + delimiter.length * 3
                }

            override fun transformedToOriginal(offset: Int): Int =
                when {
                    offset <= 4 -> offset
                    offset <= 9 -> offset - delimiter.length * 1
                    offset <= 14 -> offset - delimiter.length * 2
                    else -> offset - delimiter.length * 3
                }
        }

    override fun filter(text: AnnotatedString): TransformedText {
        val rawInput = text.text.take(maxInputLength)
        val formattedText =
            buildString {
                rawInput.forEachIndexed { index: Int, char: Char ->
                    append(char)
                    if (index % 4 == 3 && index != maxInputLength - 1) append(delimiter)
                }
            }
        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }

    companion object {
        private const val CARD_NUMBER_DELIMITER: String = " - "
    }
}

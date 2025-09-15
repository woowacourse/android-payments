package woowacourse.payments.ui.screen.cardAddition

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
            override fun originalToTransformed(offset: Int): Int {
                val firstDelimiterOriginalIndex = CARD_NUMBER_GROUP_SIZE - 1
                val secondDelimiterOriginalIndex =
                    firstDelimiterOriginalIndex + CARD_NUMBER_GROUP_SIZE
                val thirdDelimiterOriginalIndex =
                    secondDelimiterOriginalIndex + CARD_NUMBER_GROUP_SIZE

                return when {
                    offset <= firstDelimiterOriginalIndex -> offset
                    offset <= secondDelimiterOriginalIndex -> offset + delimiter.length * 1
                    offset <= thirdDelimiterOriginalIndex -> offset + delimiter.length * 2
                    else -> offset + delimiter.length * 3
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                val firstDelimiterTransformedIndex = CARD_NUMBER_GROUP_SIZE
                val secondDelimiterTransformedIndex =
                    firstDelimiterTransformedIndex + delimiter.length + CARD_NUMBER_GROUP_SIZE
                val thirdDelimiterTransformedIndex =
                    secondDelimiterTransformedIndex + delimiter.length + CARD_NUMBER_GROUP_SIZE

                return when {
                    offset <= firstDelimiterTransformedIndex -> offset
                    offset <= secondDelimiterTransformedIndex -> offset - delimiter.length * 1
                    offset <= thirdDelimiterTransformedIndex -> offset - delimiter.length * 2
                    else -> offset - delimiter.length * 3
                }
            }
        }

    override fun filter(text: AnnotatedString): TransformedText {
        val rawInput = text.text.take(maxInputLength)
        val formattedText =
            buildString {
                rawInput.forEachIndexed { index: Int, char: Char ->
                    append(char)
                    if ((index + 1) % CARD_NUMBER_GROUP_SIZE == 0 && index != maxInputLength - 1) {
                        append(
                            delimiter,
                        )
                    }
                }
            }
        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }

    companion object {
        private const val CARD_NUMBER_DELIMITER: String = " - "
        private const val CARD_NUMBER_GROUP_SIZE: Int = 4
    }
}

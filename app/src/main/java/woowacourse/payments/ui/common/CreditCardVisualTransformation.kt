package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object CreditCardVisualTransformation : VisualTransformation {
    private const val CARD_NUMBER_LENGTH = 16
    private const val CARD_NUMBER_SEPARATOR = " - "

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(CARD_NUMBER_LENGTH)

        val formatted =
            buildString {
                trimmed.forEachIndexed { index, char ->
                    if (index > 0 && index % 4 == 0) append(CARD_NUMBER_SEPARATOR)
                    append(char)
                }
            }

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int =
                    when (offset) {
                        in 1..4 -> offset
                        in 5..8 -> offset + 3
                        in 9..12 -> offset + 6
                        in 13..16 -> offset + 9
                        else -> offset
                    }

                override fun transformedToOriginal(offset: Int): Int {
                    val group = offset / 7
                    val posInGroup = offset % 7
                    return (group * 4 + posInGroup.coerceAtMost(4)).coerceAtMost(CARD_NUMBER_LENGTH)
                }
            }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}

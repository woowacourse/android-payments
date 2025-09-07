package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object CreditCardVisualTransformation : VisualTransformation {
    private const val CARD_NUMBER_LENGTH = 16
    private const val CARD_NUMBER_OFFSET = 4
    private const val CARD_NUMBER_SEPARATOR = " - "
    private const val SEPARATOR_LENGTH = CARD_NUMBER_SEPARATOR.length

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(CARD_NUMBER_LENGTH)

        val formatted =
            buildString {
                trimmed.forEachIndexed { index, char ->
                    if (index > 0 && index % CARD_NUMBER_OFFSET == 0) {
                        append(CARD_NUMBER_SEPARATOR)
                    }
                    append(char)
                }
            }

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val group = offset / CARD_NUMBER_OFFSET
                    val transformed = offset + group * SEPARATOR_LENGTH
                    return transformed.coerceAtMost(formatted.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val adjustedOffset = offset.coerceAtMost(formatted.length)

                    val group = adjustedOffset / (CARD_NUMBER_OFFSET + SEPARATOR_LENGTH)
                    val posInGroup = adjustedOffset % (CARD_NUMBER_OFFSET + SEPARATOR_LENGTH)

                    val original =
                        group * CARD_NUMBER_OFFSET + posInGroup.coerceAtMost(CARD_NUMBER_OFFSET)
                    return original.coerceAtMost(trimmed.length)
                }
            }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}

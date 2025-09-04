package woowacourse.payments.ui.newcard

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText =
        TransformedText(
            AnnotatedString(
                text.text.chunked(CARD_NUMBER_CHUNK_SIZE).joinToString(CARD_NUMBER_DELIMITER),
            ),
            translator,
        )

    companion object {
        private const val CARD_NUMBER_CHUNK_SIZE = 4
        private const val CARD_NUMBER_DELIMITER = " - "

        val translator =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val multiplier = (offset - 1).coerceAtLeast(0) / CARD_NUMBER_CHUNK_SIZE
                    return offset + CARD_NUMBER_DELIMITER.length * multiplier
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val multiplier =
                        ((offset - 1)).coerceAtLeast(0) / (CARD_NUMBER_CHUNK_SIZE + CARD_NUMBER_DELIMITER.length)
                    return (offset - (CARD_NUMBER_DELIMITER.length * multiplier)).coerceAtMost(
                        CARD_NUMBER_CHUNK_SIZE * (multiplier + 1),
                    )
                }
            }
    }
}

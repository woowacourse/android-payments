package woowacourse.payments.ui.cardform

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = text.text.chunked(CARD_NUMBER_BLOCK_SIZE).joinToString("-")

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int =
                    (offset + offset / CARD_NUMBER_BLOCK_SIZE).coerceAtMost(transformedText.length)

                override fun transformedToOriginal(offset: Int): Int =
                    (offset - offset / (CARD_NUMBER_BLOCK_SIZE + 1)).coerceAtMost(text.text.length)
            }
        return TransformedText(AnnotatedString(transformedText), offsetMapping)
    }

    companion object {
        private const val CARD_NUMBER_BLOCK_SIZE: Int = 4
    }
}

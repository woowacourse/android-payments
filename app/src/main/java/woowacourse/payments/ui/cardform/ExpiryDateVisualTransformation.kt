package woowacourse.payments.ui.cardform

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpiryDateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = text.text.chunked(EXPIRY_DATE_BLOCK_SIZE).joinToString("/")

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int) =
                    (offset + offset / EXPIRY_DATE_BLOCK_SIZE).coerceAtMost(transformedText.length)

                override fun transformedToOriginal(offset: Int) =
                    (offset - offset / (EXPIRY_DATE_BLOCK_SIZE + 1)).coerceAtMost(text.text.length)
            }

        return TransformedText(AnnotatedString(transformedText), offsetMapping)
    }

    companion object {
        private const val EXPIRY_DATE_BLOCK_SIZE: Int = 2
    }
}

package woowacourse.payments.ui.newcard

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpirationDateTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText =
        TransformedText(
            AnnotatedString(
                text.text
                    .chunked(EXPIRATION_DATE_CHUNK_SIZE)
                    .joinToString(EXPIRATION_DATE_DELIMITER),
            ),
            translator,
        )

    companion object {
        private const val EXPIRATION_DATE_CHUNK_SIZE = 2
        private const val EXPIRATION_DATE_DELIMITER = " / "

        val translator =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val multiplier = (offset - 1).coerceAtLeast(0) / EXPIRATION_DATE_CHUNK_SIZE
                    return offset + EXPIRATION_DATE_DELIMITER.length * multiplier
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val multiplier =
                        ((offset - 1)).coerceAtLeast(0) / (EXPIRATION_DATE_CHUNK_SIZE + EXPIRATION_DATE_DELIMITER.length)
                    return (offset - (EXPIRATION_DATE_DELIMITER.length * multiplier)).coerceAtMost(
                        EXPIRATION_DATE_CHUNK_SIZE * (multiplier + 1),
                    )
                }
            }
    }
}

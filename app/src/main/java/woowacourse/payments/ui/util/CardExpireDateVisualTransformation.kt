package woowacourse.payments.ui.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardExpireDateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = text.text.chunked(CHUNK_SIZE).joinToString(SEPARATOR)

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= CHUNK_SIZE) return offset
                    return offset + SEPARATOR.length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= CHUNK_SIZE) return offset
                    return offset - SEPARATOR.length
                }
            }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }

    companion object {
        private const val SEPARATOR = " / "
        private const val CHUNK_SIZE = 2
    }
}

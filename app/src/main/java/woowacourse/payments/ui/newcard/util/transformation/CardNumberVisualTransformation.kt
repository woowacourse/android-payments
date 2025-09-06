package woowacourse.payments.ui.newcard.util.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.filter { it.isDigit() }.take(MAX_LENGTH)
        val formattedText = trimmed.chunked(CHUNK_SIZE).joinToString(SEPARATOR)

        return TransformedText(
            AnnotatedString(formattedText),
            CardNumberOffsetMapping(trimmed, formattedText),
        )
    }

    companion object {
        private const val MAX_LENGTH = 16
        private const val CHUNK_SIZE = 4
        private const val SEPARATOR = " - "
    }
}

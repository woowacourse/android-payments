package woowacourse.payments.ui.newcard.util.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import woowacourse.payments.ui.model.ExpirationDateUiModel.Companion.EXPIRATION_DATE_LENGTH

class ExpirationDateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.filter { it.isDigit() }.take(EXPIRATION_DATE_LENGTH)
        val formattedText = trimmed.chunked(CHUNK_SIZE).joinToString(SEPARATOR)

        return TransformedText(
            AnnotatedString(formattedText),
            ExpirationDateOffsetMapping(trimmed, formattedText),
        )
    }

    companion object {
        private const val CHUNK_SIZE = 2
        private const val SEPARATOR = " / "
    }
}

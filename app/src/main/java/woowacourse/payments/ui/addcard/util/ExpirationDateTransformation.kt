package woowacourse.payments.ui.addcard.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpirationDateTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val cardNumberWithHyphens = text.chunked(2).joinToString("/")
        return TransformedText(
            AnnotatedString(
                text = cardNumberWithHyphens,
            ), ExpirationDateOffset(text.text)
        )
    }
}

private class ExpirationDateOffset(
    private val expirationDate: String
) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        return expirationDate.chunked(2).joinToString("/").length
    }

    override fun transformedToOriginal(offset: Int): Int {
        return expirationDate.length
    }
}
package woowacourse.payments.ui.addcard.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpirationDateFieldTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val cardNumberWithHyphens = text.chunked(2).joinToString("/")
        return TransformedText(
            AnnotatedString(
                text = cardNumberWithHyphens,
            ), ExpirationDateOffset()
        )
    }
}

private class ExpirationDateOffset : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = offset + ((offset - 1) / 2)

    override fun transformedToOriginal(offset: Int): Int = offset - (offset / 3)
}
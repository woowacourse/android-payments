package woowacourse.payments.ui.addcard.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberFieldTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val cardNumberWithHyphens = text.chunked(4).joinToString("-")
        return TransformedText(
            AnnotatedString(
                text = cardNumberWithHyphens,
            ), CardNumberOffsetMapping()
        )
    }
}

private class CardNumberOffsetMapping : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = offset + ((offset - 1) / 4)

    override fun transformedToOriginal(offset: Int): Int = offset - (offset / 5)
}
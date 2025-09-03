package woowacourse.payments.ui.addcard.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val cardNumberWithHyphens = text.chunked(4).joinToString("-")
        return TransformedText(
            AnnotatedString(
                text = cardNumberWithHyphens,
            ), CardNumberOffsetMapping(text.text)
        )
    }
}

private class CardNumberOffsetMapping(
    private val cardNumber: String
) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        return cardNumber.chunked(4).joinToString("-").length
    }

    override fun transformedToOriginal(offset: Int): Int {
        return cardNumber.length
    }
}
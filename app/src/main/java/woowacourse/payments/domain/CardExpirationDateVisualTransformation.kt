package woowacourse.payments.domain

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardExpirationDateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        for (i in text.indices) {
            out += text[i]
            if (i == 1) {
                out += "/"
            }
        }

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 1) return offset
                    if (offset <= 4) return offset + 1
                    return 5
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 2) return offset
                    if (offset <= 5) return offset - 1
                    return 4
                }
            }

        return TransformedText(androidx.compose.ui.text.AnnotatedString(out), offsetMapping)
    }
}
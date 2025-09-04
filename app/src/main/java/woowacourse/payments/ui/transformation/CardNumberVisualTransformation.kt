package woowacourse.payments.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText =  text.filter { it.isDigit() }
            .take(16)
            .chunked(4)
            .joinToString(" - ")

        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return formattedText.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                return text.length
            }
        }
        return TransformedText(
            androidx.compose.ui.text.AnnotatedString(formattedText),
            offsetTranslator
        )
    }
}

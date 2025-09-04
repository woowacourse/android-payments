package woowacourse.payments

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.ui.text.input.OffsetMapping

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val trimmed = if (originalText.length >= 16) originalText.substring(0..15) else originalText

        val formatted = trimmed.chunked(4).joinToString(" - ")

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset + (offset / 4)
            }

            override fun transformedToOriginal(offset: Int): Int {
                return offset - (offset / 5)
            }
        }

        return TransformedText(AnnotatedString(formatted), numberOffsetTranslator)
    }
}


package woowacourse.payments

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardExpiryDateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val trimmed = if (originalText.length >= 4) originalText.substring(0..3) else originalText

        val formatted = StringBuilder()
        trimmed.forEachIndexed { index, char ->
            formatted.append(char)
            if (index == 1 && trimmed.length > 2) {
                formatted.append('/')
            }
        }

        val dateOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return if (offset <= 1) offset else offset + 1
            }

            override fun transformedToOriginal(offset: Int): Int {
                return if (offset <= 2) offset else offset - 1
            }
        }

        return TransformedText(AnnotatedString(formatted.toString()), dateOffsetTranslator)
    }
}

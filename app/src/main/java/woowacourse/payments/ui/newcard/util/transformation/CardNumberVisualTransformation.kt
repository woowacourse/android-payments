package woowacourse.payments.ui.newcard.util.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.filter { it.isDigit() }.take(16)

        val formattedText = trimmed.chunked(4).joinToString(" - ")

        val offsetTranslator =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 0) return 0
                    if (offset >= trimmed.length) return formattedText.length

                    val sepCount = (offset - 1) / 4
                    return offset + sepCount * 3
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 0) return 0
                    if (offset >= formattedText.length) return trimmed.length

                    val sepCount = offset / 7
                    return (offset - sepCount * 3).coerceIn(0, trimmed.length)
                }
            }
        return TransformedText(
            AnnotatedString(formattedText),
            offsetTranslator,
        )
    }
}

package woowacourse.payments.ui.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpiredVisualTransformation(
    private val groupSize: Int = 2,
    private val delimiter: String = "/",
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }.take(4)
        val formatted = digits.chunked(groupSize).joinToString(delimiter)

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= groupSize) return offset
                    return (offset + delimiter.length).coerceAtMost(formatted.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= groupSize) return offset
                    return (offset - delimiter.length).coerceAtMost(digits.length)
                }
            }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}

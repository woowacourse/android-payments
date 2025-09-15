package woowacourse.payments.ui.format

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class UniformlySeparatingVisualTransformation(
    val chunkSize: Int,
    val separator: String,
) : VisualTransformation {
    private val offsetMapping =
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val multiplier = (offset - 1).coerceAtLeast(0) / chunkSize
                return offset + separator.length * multiplier
            }

            override fun transformedToOriginal(offset: Int): Int {
                val multiplier =
                    ((offset - 1)).coerceAtLeast(0) / (chunkSize + separator.length)
                return (offset - (separator.length * multiplier)).coerceAtMost(
                    chunkSize * (multiplier + 1),
                )
            }
        }

    override fun filter(text: AnnotatedString): TransformedText =
        TransformedText(
            AnnotatedString(text.text.chunked(chunkSize).joinToString(separator)),
            offsetMapping,
        )
}

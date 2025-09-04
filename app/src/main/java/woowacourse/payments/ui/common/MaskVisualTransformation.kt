package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.min

class MaskVisualTransformation(
    private val maskingLength: Int,
    private val maskingChar: Char = DEFAULT_MASKING_CHAR,
) : VisualTransformation {
    private val offsetMapping =
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = offset

            override fun transformedToOriginal(offset: Int): Int = offset
        }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.trim()
        val length = trimmed.length

        val formatted =
            buildString {
                append(trimmed.substring(0, maxOf(0, length - maskingLength)))
                repeat(min(maskingLength, length)) {
                    append(maskingChar)
                }
            }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }

    companion object {
        private const val DEFAULT_MASKING_CHAR = '•'
    }
}

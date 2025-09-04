package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object ExpirationDateVisualTransformation : VisualTransformation {
    private const val EXPIRATION_DATE_LENGTH = 4
    private const val EXPIRATION_DATE_SEPARATOR = " / "

    private val offsetMapping =
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int =
                when (offset) {
                    in 1..2 -> offset
                    in 3..4 -> offset + 3
                    else -> offset
                }

            override fun transformedToOriginal(offset: Int): Int {
                val group = offset / 5
                val posInGroup = offset % 5
                return (group * 2 + posInGroup.coerceAtMost(1)).coerceAtMost(EXPIRATION_DATE_LENGTH)
            }
        }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(EXPIRATION_DATE_LENGTH)
        val formatted =
            buildString {
                trimmed.forEachIndexed { index, char ->
                    if (index > 0 && index % 2 == 0) append(EXPIRATION_DATE_SEPARATOR)
                    append(char)
                }
            }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}

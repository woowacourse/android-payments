package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object ExpirationDateVisualTransformation : VisualTransformation {
    private const val EXPIRATION_DATE_LENGTH = 4
    private const val MONTH_LENGTH = 2
    private const val EXPIRATION_DATE_SEPARATOR = " / "
    private const val SEPARATOR_OFFSET = EXPIRATION_DATE_SEPARATOR.length
    private const val SEPARATOR_INSERT_INDEX = MONTH_LENGTH

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(EXPIRATION_DATE_LENGTH)
        val formatted =
            buildString {
                trimmed.forEachIndexed { index, char ->
                    if (index == SEPARATOR_INSERT_INDEX) append(EXPIRATION_DATE_SEPARATOR)
                    append(char)
                }
            }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }

    private val offsetMapping =
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int =
                when {
                    offset <= MONTH_LENGTH -> offset
                    offset <= EXPIRATION_DATE_LENGTH -> offset + SEPARATOR_OFFSET
                    else -> offset
                }

            override fun transformedToOriginal(offset: Int): Int =
                when {
                    offset <= MONTH_LENGTH -> offset
                    offset <= MONTH_LENGTH + SEPARATOR_OFFSET -> MONTH_LENGTH
                    offset <= EXPIRATION_DATE_LENGTH + SEPARATOR_OFFSET -> offset - SEPARATOR_OFFSET
                    else -> offset
                }
        }
}

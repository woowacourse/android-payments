package woowacourse.payments.ui.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardExpirationDateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmedText =
            if (text.text.length >= EXPIRATION_DATE_MAX_LENGTH) {
                text.text.substring(0, EXPIRATION_DATE_MAX_LENGTH)
            } else {
                text.text
            }

        var transformedText = DEFAULT_OUT
        for (index in trimmedText.indices) {
            transformedText += trimmedText[index]
            if (index == MONTH_PART_LENGTH - 1) {
                transformedText += SEPARATOR
            }
        }

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int =
                    if (offset <= MONTH_PART_LENGTH - 1) offset else offset + SEPARATOR.length

                override fun transformedToOriginal(offset: Int): Int =
                    if (offset <= MONTH_PART_LENGTH) offset else offset - SEPARATOR.length
            }

        return TransformedText(AnnotatedString(transformedText), offsetMapping)
    }

    companion object {
        private const val EXPIRATION_DATE_MAX_LENGTH = 4
        private const val MONTH_PART_LENGTH = 2
        private const val DEFAULT_OUT = ""
        private const val SEPARATOR = "/"
    }
}

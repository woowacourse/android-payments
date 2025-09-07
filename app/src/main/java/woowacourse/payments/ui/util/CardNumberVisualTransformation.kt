package woowacourse.payments.ui.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.min

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmedText =
            if (text.text.length >= CARD_NUMBER_MAX_LENGTH) {
                text.text.substring(0, CARD_NUMBER_MAX_LENGTH)
            } else {
                text.text
            }

        var transformedText = DEFAULT_OUT
        for (index in trimmedText.indices) {
            transformedText += trimmedText[index]
            if (index % CARD_GROUP_SIZE == CARD_GROUP_SIZE - 1 && index < CARD_NUMBER_MAX_LENGTH - 1) {
                transformedText += SEPARATOR
            }
        }

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int =
                    min(offset + (offset / CARD_GROUP_SIZE), CARD_NUMBER_INCLUDE_HYPHEN_MAX_LENGTH)

                override fun transformedToOriginal(offset: Int): Int = offset - (offset / (CARD_GROUP_SIZE + 1))
            }

        return TransformedText(AnnotatedString(transformedText), offsetMapping)
    }

    companion object {
        private const val CARD_NUMBER_MAX_LENGTH = 16
        private const val CARD_GROUP_SIZE = 4

        private const val CARD_NUMBER_INCLUDE_HYPHEN_MAX_LENGTH =
            CARD_NUMBER_MAX_LENGTH + (CARD_NUMBER_MAX_LENGTH / CARD_GROUP_SIZE - 1)

        private const val DEFAULT_OUT = ""
        private const val SEPARATOR = "-"
    }
}

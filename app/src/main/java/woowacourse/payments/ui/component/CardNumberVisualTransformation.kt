package woowacourse.payments.ui.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.min

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed =
            if (text.text.length >= CARD_NUMBER_MAX_LENGTH) {
                text.text.substring(0, CARD_NUMBER_MAX_LENGTH)
            } else {
                text.text
            }

        var out = DEFAULT_OUT
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i % CARD_GROUP_SIZE == CARD_GROUP_SIZE - 1 && i < CARD_NUMBER_MAX_LENGTH - 1) {
                out += SEPARATOR
            }
        }

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int =
                    min(offset + (offset / CARD_GROUP_SIZE), CARD_NUMBER_INCLUDE_HYPHEN_MAX_LENGTH)

                override fun transformedToOriginal(offset: Int): Int =
                    offset - (offset / (CARD_GROUP_SIZE + 1))
            }

        return TransformedText(AnnotatedString(out), offsetMapping)
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

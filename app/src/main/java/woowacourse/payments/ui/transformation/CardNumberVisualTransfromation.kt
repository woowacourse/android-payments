package woowacourse.payments.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.ui.text.input.OffsetMapping

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val trimmedOriginal = if (originalText.length >= CARD_NUMBER_MAX_LENGTH) {
            originalText.substring(0 until CARD_NUMBER_MAX_LENGTH)
        } else {
            originalText
        }

        val formatted = trimmedOriginal
            .chunked(CARD_NUMBER_GROUP_SIZE)
            .joinToString(CARD_NUMBER_SEPARATOR)

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {

                val separatorsAdded = (offset / CARD_NUMBER_GROUP_SIZE) * CARD_NUMBER_SEPARATOR.length

                val transformedOffset = offset + separatorsAdded

                return transformedOffset.coerceAtMost(formatted.length)
            }

            override fun transformedToOriginal(offset: Int): Int {

                val separatorsCount = (offset / (CARD_NUMBER_GROUP_SIZE + CARD_NUMBER_SEPARATOR.length)) * CARD_NUMBER_SEPARATOR.length

                val originalOffset = offset - separatorsCount

                return originalOffset.coerceAtMost(trimmedOriginal.length)
            }
        }

        return TransformedText(AnnotatedString(formatted), numberOffsetTranslator)
    }
}

const val CARD_NUMBER_MAX_LENGTH = 16
const val CARD_NUMBER_GROUP_SIZE = 4
const val CARD_NUMBER_SEPARATOR = "-"


package woowacourse.payments.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardExpiryDateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val trimmedOriginal =
            if (originalText.length >= CARD_EXPIRY_DATE_MAX_LENGTH) {
                originalText.substring(0 until CARD_EXPIRY_DATE_MAX_LENGTH)
            } else {
                originalText
            }

        val formatted = StringBuilder()
        trimmedOriginal.forEachIndexed { index, char ->
            formatted.append(char)
            if (index == CARD_EXPIRY_DATE_FIRST_GROUP_SIZE - 1 && trimmedOriginal.length > CARD_EXPIRY_DATE_FIRST_GROUP_SIZE) {
                formatted.append(CARD_EXPIRY_DATE_GROUP_SEPARATOR)
            }
        }
        val finalFormattedString = formatted.toString()

        val dateOffsetTranslator =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val separatorsAdded =
                        if (offset >= CARD_EXPIRY_DATE_FIRST_GROUP_SIZE) {
                            CARD_EXPIRY_DATE_GROUP_SEPARATOR.length
                        } else {
                            0
                        }

                    val transformedOffset = offset + separatorsAdded
                    return transformedOffset.coerceAtMost(finalFormattedString.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val separatorsRemoved =
                        if (offset >= CARD_EXPIRY_DATE_FIRST_GROUP_SIZE + CARD_EXPIRY_DATE_GROUP_SEPARATOR.length) {
                            CARD_EXPIRY_DATE_GROUP_SEPARATOR.length
                        } else {
                            0
                        }

                    val originalOffset = offset - separatorsRemoved
                    return originalOffset.coerceAtMost(trimmedOriginal.length)
                }
            }

        return TransformedText(AnnotatedString(finalFormattedString), dateOffsetTranslator)
    }
}

private const val CARD_EXPIRY_DATE_MAX_LENGTH = 4
private const val CARD_EXPIRY_DATE_GROUP_SEPARATOR = "/"
private const val CARD_EXPIRY_DATE_FIRST_GROUP_SIZE = 2

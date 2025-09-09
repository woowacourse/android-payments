package woowacourse.payments.ui.formatter

import androidx.compose.ui.text.AnnotatedString

object CardNumberFormat {
    const val REQUIRED_LENGTH = 16
    private const val CHUNK_SIZE = 4
    private const val SEPARATOR = " - "
    private const val MASK_SIZE = 8
    private const val MASK = "*"
    val visualTransformation =
        UniformlySeparatingVisualTransformation(
            CHUNK_SIZE,
            SEPARATOR,
        )

    fun formattedCardNumber(
        text: String,
        applyMask: Boolean = false,
    ): String {
        val processedText: String =
            if (applyMask) {
                text.take(text.length - MASK_SIZE) + MASK.repeat(MASK_SIZE)
            } else {
                text
            }
        return visualTransformation.filter(AnnotatedString(processedText)).text.text
    }
}

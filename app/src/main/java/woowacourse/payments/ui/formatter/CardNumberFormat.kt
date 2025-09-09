package woowacourse.payments.ui.formatter

import androidx.compose.ui.text.AnnotatedString

object CardNumberFormat {
    const val REQUIRED_LENGTH = 16
    private const val CHUNK_SIZE = 4
    private const val SEPARATOR = " - "
    val visualTransformation =
        UniformlySeparatingVisualTransformation(
            CHUNK_SIZE,
            SEPARATOR,
        )

    fun formattedCardNumber(text: String): String = visualTransformation.filter(AnnotatedString(text)).text.text
}

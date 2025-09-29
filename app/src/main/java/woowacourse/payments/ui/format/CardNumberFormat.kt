package woowacourse.payments.ui.format

import androidx.compose.ui.text.AnnotatedString
import woowacourse.payments.domain.CardNumber

object CardNumberFormat {
    const val REQUIRED_LENGTH = 16
    private const val CHUNK_SIZE = 4
    private const val SEPARATOR = " - "
    private const val SHOW_SIZE = 8
    private const val MASK = "*"
    val visualTransformation =
        UniformlySeparatingVisualTransformation(
            CHUNK_SIZE,
            SEPARATOR,
        )

    fun formatted(cardNumber: String): String {
        val maskedText: String =
            cardNumber.take(SHOW_SIZE) + MASK.repeat((cardNumber.length - SHOW_SIZE).coerceAtLeast(0))
        return visualTransformation.filter(AnnotatedString(maskedText)).text.text
    }
}

package woowacourse.payments.ui.formatter

import androidx.compose.ui.text.AnnotatedString
import woowacourse.payments.domain.ExpirationDate
import java.time.format.DateTimeFormatter

object ExpirationDateFormat {
    const val REQUIRED_LENGTH = 4
    private const val CHUNK_SIZE = 2
    private const val SEPARATOR = " / "
    val formatPattern: DateTimeFormatter = DateTimeFormatter.ofPattern("MMyy")
    val visualTransformation =
        UniformlySeparatingVisualTransformation(
            CHUNK_SIZE,
            SEPARATOR,
        )

    fun formattedExpirationDate(expirationDate: ExpirationDate): String =
        visualTransformation.filter(AnnotatedString(expirationDate.value.format(formatPattern))).text.text
}

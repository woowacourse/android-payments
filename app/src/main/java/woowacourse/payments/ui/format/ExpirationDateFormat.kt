package woowacourse.payments.ui.format

import androidx.compose.ui.text.AnnotatedString
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

    fun formatted(expirationDate: String): String =
        visualTransformation.filter(AnnotatedString(expirationDate.format(formatPattern))).text.text
}

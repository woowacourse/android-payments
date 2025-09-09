package woowacourse.payments.ui.formatter

import androidx.compose.ui.text.AnnotatedString
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class ExpirationDateFormatter {
    val visualTransformation =
        UniformlySeparatingVisualTransformation(
            EXPIRATION_DATE_CHUNK_SIZE,
            EXPIRATION_DATE_SEPARATOR,
        )

    fun format(yearMonth: YearMonth): String =
        visualTransformation.filter(AnnotatedString(yearMonth.format(EXPIRATION_DATE_PATTERN))).text.text

    companion object {
        private const val EXPIRATION_DATE_CHUNK_SIZE = 2
        private const val EXPIRATION_DATE_SEPARATOR = " / "
        private val EXPIRATION_DATE_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("MMyy")
    }
}

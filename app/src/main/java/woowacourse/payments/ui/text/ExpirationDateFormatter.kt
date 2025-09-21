package woowacourse.payments.ui.text

import woowacourse.payments.domain.model.ExpirationDate
import java.time.format.DateTimeFormatter

object ExpirationDateFormatter {
    private val formatter = DateTimeFormatter.ofPattern(EXPIRATION_DATE_FORMAT)

    fun format(expirationDate: ExpirationDate): String {
        val raw = expirationDate.value.format(formatter)
        return raw.chunked(EXPIRATION_CHUNK_SIZE).joinToString(EXPIRATION_DATE_SEPARATOR)
    }

    private const val EXPIRATION_DATE_FORMAT = "MMyy"
    private const val EXPIRATION_DATE_SEPARATOR = " / "
    private const val EXPIRATION_CHUNK_SIZE = 2
}

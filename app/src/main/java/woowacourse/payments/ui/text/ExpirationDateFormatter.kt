package woowacourse.payments.ui.text

import woowacourse.payments.domain.model.ExpirationDate
import java.time.format.DateTimeFormatter

object ExpirationDateFormatter {
    private val formatter = DateTimeFormatter.ofPattern(EXPIRATION_DATE_FORMAT)

    fun format(expirationDate: ExpirationDate): String = expirationDate.value.format(formatter)

    private const val EXPIRATION_DATE_FORMAT = "MM / yy"
}

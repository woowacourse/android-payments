package woowacourse.payments.ui.utils.ext

import woowacourse.payments.domain.ExpiryException
import java.time.YearMonth

// 추후 도메인으로 이전
fun String.formatExpiryException(): ExpiryException? {
    val digits = this.filter(Char::isDigit)
    if (digits.length != 4) return ExpiryException.InvalidFormat

    val mm = digits.substring(0, 2).toIntOrNull() ?: return ExpiryException.InvalidMonth
    val yy = digits.substring(2, 4).toIntOrNull() ?: return ExpiryException.InvalidYear
    if (mm !in 1..12) return ExpiryException.InvalidMonth

    val now = YearMonth.now()
    val exp = YearMonth.of(2000 + yy, mm)
    if (exp.isBefore(now)) return ExpiryException.Expired
    return null
}

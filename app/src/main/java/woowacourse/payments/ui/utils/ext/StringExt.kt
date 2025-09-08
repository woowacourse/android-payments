package woowacourse.payments.ui.utils.ext

import woowacourse.payments.domain.CardExpiryException
import java.time.YearMonth

// 추후 도메인으로 이전
fun String.formatCardExpiryException(): CardExpiryException? {
    val digits = this.filter(Char::isDigit)

    if (digits.length != 4) return null
    val mm = digits.substring(0, 2).toIntOrNull() ?: return CardExpiryException.InvalidMonth
    val yy = digits.substring(2, 4).toIntOrNull() ?: return CardExpiryException.InvalidYear
    if (mm !in 1..12) return CardExpiryException.InvalidMonth

    val now = YearMonth.now()
    val exp = YearMonth.of(2000 + yy, mm)
    if (exp.isBefore(now)) return CardExpiryException.Expired
    return null
}

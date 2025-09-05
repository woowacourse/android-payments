package woowacourse.payments.domain

import java.time.Month

class ExpirationDateUiModel(
    val value: String,
) {
    fun isValidMonth(): Boolean {
        if (value.length < 2) return true
        return value.substring(0, 2).toInt() in Month.JANUARY.value..Month.DECEMBER.value
    }
}

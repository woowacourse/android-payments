package woowacourse.payments.domain

import java.time.Month
import java.time.Year

data class ExpirationDate(
    val month: Month,
    val year: Year,
)

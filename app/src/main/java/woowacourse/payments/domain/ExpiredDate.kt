package woowacourse.payments.domain

class ExpiredDate(
    val monthYear: String
) {
    init {
        requireAllDigit()
        requireValidLength()
        requireValidMonth()
    }

    private fun requireAllDigit() = require(monthYear.all { it.isDigit() })

    private fun requireValidLength() = require(monthYear.length <= 4)

    private fun requireValidMonth() {
        if (monthYear.isNotEmpty()) {
            require(monthYear.startsWith("0") || monthYear.startsWith("1"))
        }
        if (monthYear.length >= 2) {
            require(monthYear.substring(0, 2).toInt() in 1..12)
        }
    }
}

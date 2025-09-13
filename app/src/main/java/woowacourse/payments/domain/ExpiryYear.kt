package woowacourse.payments.domain

@JvmInline
value class ExpiryYear(val value: Int) {
    init {
        require(value in 0..99) { "년도는 00~99여야 합니다." }
    }

    fun toFourDigitYear(): Int = 2000 + value
}

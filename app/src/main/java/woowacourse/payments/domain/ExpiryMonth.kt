package woowacourse.payments.domain

@JvmInline
value class ExpiryMonth(val value: Int) {
    init {
        require(value in 1..12) { "월은 01~12여야 합니다." }
    }

    override fun toString(): String = value.toString().padStart(2, '0')
}

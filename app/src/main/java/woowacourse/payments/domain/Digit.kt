package woowacourse.payments.domain

@JvmInline
value class Digit (
    val value: Int,
) {
    init {
        require(value in MINIMUM_VALUE .. MAXIMUM_VALUE) { "숫자는 ${MINIMUM_VALUE}부터 ${MAXIMUM_VALUE}여야 합니다." }
    }

    companion object {
        private const val MINIMUM_VALUE = 0
        private const val MAXIMUM_VALUE = 9
    }
}
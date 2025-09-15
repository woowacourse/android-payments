package woowacourse.payments.domain

@JvmInline
value class CardholderName(
    val value: String,
) {
    init {
        require(value.length <= CARDHOLDER_NAME_MAX_LENGTH) { MAX_LENGTH_ERROR_MESSAGE }
    }

    companion object {
        const val CARDHOLDER_NAME_MAX_LENGTH = 30
        private const val MAX_LENGTH_ERROR_MESSAGE =
            "카드 소유자 이름은 ${CARDHOLDER_NAME_MAX_LENGTH}자 이하여야 합니다."
    }
}

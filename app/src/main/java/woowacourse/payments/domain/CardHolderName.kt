package woowacourse.payments.domain

@JvmInline
value class CardHolderName(
    val name: String,
) {
    init {
        require(name.length <= MAX_NAME_LENGTH) { ERROR_INVALID_NAME_LENGTH }
    }

    companion object {
        const val MAX_NAME_LENGTH = 30
        private const val ERROR_INVALID_NAME_LENGTH = "카드 소유자 이름은 30자 이하여야 합니다."
    }
}

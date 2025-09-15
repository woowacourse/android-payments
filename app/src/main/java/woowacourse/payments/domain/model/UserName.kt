package woowacourse.payments.domain.model

@JvmInline
value class UserName private constructor(
    val value: String,
) {
    init {
        require(value.length <= CARDHOLDER_NAME_MAX_LENGTH) {
            "카드 소유자 이름은 ${CARDHOLDER_NAME_MAX_LENGTH}자를 초과할 수 없습니다."
        }
    }

    companion object {
        const val CARDHOLDER_NAME_MAX_LENGTH = 30

        fun from(value: String): UserName = UserName(value)
    }
}

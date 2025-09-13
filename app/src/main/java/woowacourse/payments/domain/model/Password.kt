package woowacourse.payments.domain.model

data class Password private constructor(
    val value: String,
) {
    init {
        require(value.length == PASSWORD_LENGTH) { "비밀번호는 4자리여야 합니다." }

        require(value.all { it.isDigit() }) { "비밀번호는 숫자로만 구성되어야 합니다." }
    }

    companion object {
        const val PASSWORD_LENGTH = 4

        fun from(value: String): Password = Password(value)
    }
}

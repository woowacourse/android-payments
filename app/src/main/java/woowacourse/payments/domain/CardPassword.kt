package woowacourse.payments.domain

@JvmInline
value class CardPassword(val value: List<Digit>) {
    constructor(value: String) : this(value.map { Digit(it.digitToInt()) })

    init {
        require(value.size == CARD_PASSWORD_SIZE) { "카드 비밀번호는 ${CARD_PASSWORD_SIZE}여야 합니다." }
    }

    override fun toString(): String = value.joinToString("") { it.value.toString() }

    companion object {
        private const val CARD_PASSWORD_SIZE = 4
    }
}

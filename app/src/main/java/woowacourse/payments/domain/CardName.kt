package woowacourse.payments.domain

@JvmInline
value class CardName private constructor(val value: String) {
    init {
        require(value.length <= MAXIMUM_LENGTH) { "카드 이름은 최대 ${MAXIMUM_LENGTH}를 초과할 수 없습니다." }
        require(value.all { it.isEnglishLetterOrSpace() }) { "카드 이름은 영어와 공백으로만 이루어져 있습니다." }
    }

    companion object {
        private const val MAXIMUM_LENGTH = 30

        operator fun invoke(raw: String?): CardName {
            return CardName(raw?.uppercase() ?: "")
        }
    }
}

private fun Char.isEnglishLetterOrSpace(): Boolean {
    return this == ' ' || (this in 'A'..'Z')
}

package woowacourse.payments.domain

@JvmInline
value class CardHolderName(
    val cardHolderName: String,
) {
    init {
        require(cardHolderName.length <= 30) {
            "카드 소지자 이름은 30자 이하여야 합니다."
        }

        require(cardHolderName.all { it.isUpperCase() || it.isWhitespace() }) {
            "카드 소지자 이름은 대문자 알파벳으로만 구성되어야 합니다."
        }
    }
}

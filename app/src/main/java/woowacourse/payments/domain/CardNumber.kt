package woowacourse.payments.domain

@JvmInline
value class CardNumber(
    val number: String,
) {
    init {
        require(number.length == 16 && number.all { it.isDigit() }) {
            "카드 번호는 16자리 숫자여야 합니다."
        }
    }
}

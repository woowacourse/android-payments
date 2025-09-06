package woowacourse.payments.domain

class CardNumber(
    val numbers: String
) {
    init {
        require(numbers.all { it.isDigit() }) { "카드번호는 숫자여야 합니다." }
        require(numbers.length == 16) { "카드번호는 16자 길이입니다." }
    }
}

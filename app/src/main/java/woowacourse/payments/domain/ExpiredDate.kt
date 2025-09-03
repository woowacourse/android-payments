package woowacourse.payments.domain

class ExpiredDate(
    val month: Int,
    val year: Int
) {
    init {
        require(month in MIN_MONTH..MAX_MONTH) { "카드 유효기간은 1~12월 사이여야 합니다." }
        require(year >= THIS_YEAR) { "카드 유효기간은 ${THIS_YEAR}년도 이상이어야 합니다." }
    }

    companion object {
        private const val MIN_MONTH = 1
        private const val MAX_MONTH = 12
        private const val THIS_YEAR = 25
    }
}

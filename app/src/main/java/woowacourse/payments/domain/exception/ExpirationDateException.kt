package woowacourse.payments.domain.exception

sealed class ExpirationDateException(
    override val message: String
) : Throwable() {
        data object ExpirationDateMonthException : ExpirationDateException("월은 1~12 사이입니다.")
    data object ExpirationDateYearAfterNowException : ExpirationDateException("만료된 카드입니다.")
    data object ExpirationDateYearBeforeFiveYearsException : ExpirationDateException("년은 현재 년도 이후 5년 이내입니다.")
}
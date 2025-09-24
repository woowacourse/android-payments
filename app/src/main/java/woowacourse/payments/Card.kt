package woowacourse.payments

import java.time.YearMonth

data class Card(
    val bankType: BankType,
    val number: String,
    val expiredDate: YearMonth,
    val password: String,
    val holder: String? = null,
) {
    init {
        require(number.length == NUMBER_LENGTH) { "카드 번호는 ${NUMBER_LENGTH}자리여야 합니다." }
        require(password.length == PASSWORD_LENGTH) { "카드 비밀번호는 ${PASSWORD_LENGTH}자리여야 합니다." }
    }

    /**
     * 만료일을 "MMYY" 형식 문자열로 받아서 [YearMonth]로 변환합니다.
     *
     * 예시:
     * - "0125" → 2025년 1월
     * - "1299" → 2099년 12월
     *
     * @param expiredDate "MMYY" 형식의 만료일 문자열
     */
    constructor(
        bankType: BankType,
        number: String,
        expiredDate: String,
        password: String,
        holder: String? = null,
    ) : this(
        bankType = bankType,
        number = number,
        expiredDate = expiredDate.toYearMonth(),
        password = password,
        holder = holder,
    )

    companion object {
        const val NUMBER_LENGTH: Int = 16
        const val EXPIRED_DATE_LENGTH: Int = 4
        const val PASSWORD_LENGTH: Int = 4

        private fun String.toYearMonth(): YearMonth {
            require(this.length == EXPIRED_DATE_LENGTH) { "만료일은 ${EXPIRED_DATE_LENGTH}자리여야 합니다." }

            val month = this.take(2).toInt()
            val year = 2000 + this.takeLast(2).toInt()

            return YearMonth.of(year, month)
        }
    }
}

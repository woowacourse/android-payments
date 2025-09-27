package woowacourse.payments.domain

import java.time.YearMonth

data class Card(
    val bankType: BankType,
    val number: CardNumber,
    val expiredDate: YearMonth,
    val password: CardPassword,
    val holder: String? = null,
) {
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
        number: CardNumber,
        expiredDate: String,
        password: CardPassword,
        holder: String? = null,
    ) : this(
        bankType = bankType,
        number = number,
        expiredDate = expiredDate.toYearMonth(),
        password = password,
        holder = holder,
    )

    companion object {
        const val EXPIRED_DATE_LENGTH: Int = 4

        operator fun invoke(
            bankType: BankType,
            number: String,
            expiredDate: YearMonth,
            password: CardPassword,
            holder: String? = null,
        ) = Card(
            bankType = bankType,
            number = CardNumber(number),
            expiredDate = expiredDate,
            password = password,
            holder = holder,
        )

        operator fun invoke(
            bankType: BankType,
            number: String,
            expiredDate: String,
            password: CardPassword,
            holder: String? = null,
        ) = Card(
            bankType = bankType,
            number = CardNumber(number),
            expiredDate = expiredDate,
            password = password,
            holder = holder,
        )

        private fun String.toYearMonth(): YearMonth {
            require(this.length == EXPIRED_DATE_LENGTH) { "만료일은 ${EXPIRED_DATE_LENGTH}자리여야 합니다." }

            val month = this.take(2).toInt()
            val year = 2000 + this.takeLast(2).toInt()

            return YearMonth.of(year, month)
        }
    }
}

package woowacourse.payments.domain

import java.time.YearMonth

data class Card(
    val cardCompany: CardCompany,
    val number: CardNumber,
    val expiredDate: ExpiredDate,
    val ownerName: OwnerName,
    val password: Password,
) {
    companion object {
        fun Card(
            cardCompany: CardCompany,
            number: String,
            expiredDate: YearMonth,
            ownerName: String,
            password: String,
        ) = Card(
            cardCompany = cardCompany,
            number = CardNumber(number),
            expiredDate = ExpiredDate(expiredDate),
            ownerName = OwnerName(ownerName),
            password = Password(password),
        )
    }
}









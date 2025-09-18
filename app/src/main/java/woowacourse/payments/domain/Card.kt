package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.YearMonth

@Parcelize
data class Card(
    val cardCompany: CardCompany,
    val number: CardNumber,
    val expirationDate: ExpirationDate,
    val ownerName: OwnerName,
    val password: Password,
) : Parcelable {
    companion object {
        fun Card(
            cardCompany: CardCompany,
            number: String,
            expirationDate: YearMonth,
            ownerName: String,
            password: String,
        ) = Card(
            cardCompany = cardCompany,
            number = CardNumber(number),
            expirationDate = ExpirationDate(expirationDate),
            ownerName = OwnerName(ownerName),
            password = Password(password),
        )
    }
}









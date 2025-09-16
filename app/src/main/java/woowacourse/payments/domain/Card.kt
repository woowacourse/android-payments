package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Parcelize
data class Card(
    val bankType: BankType,
    val number: CardNumber,
    val expirationDate: ExpirationDate,
    val ownerName: OwnerName,
    val password: Password,
) : Parcelable {
    companion object {
        fun Card(
            bankType: BankType,
            number: String,
            expirationDate: String,
            ownerName: String,
            password: String,
        ) = Card(
            bankType,
            CardNumber(number),
            ExpirationDate(YearMonth.parse(expirationDate, DateTimeFormatter.ofPattern("MMyy"))),
            OwnerName(ownerName),
            Password(password),
        )
    }
}









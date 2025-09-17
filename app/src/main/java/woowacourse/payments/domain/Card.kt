package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.newcard.uiModel.BankTypeUiModel
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Parcelize
data class Card(
    val bankTypeUiModel: BankTypeUiModel,
    val number: CardNumber,
    val expirationDate: ExpirationDate,
    val ownerName: OwnerName,
    val password: Password,
) : Parcelable {
    companion object {
        fun Card(
            bankTypeUiModel: BankTypeUiModel,
            number: String,
            expirationDate: YearMonth,
            ownerName: String,
            password: String,
        ) = Card(
            bankTypeUiModel,
            CardNumber(number),
            ExpirationDate(expirationDate),
            OwnerName(ownerName),
            Password(password),
        )
    }
}









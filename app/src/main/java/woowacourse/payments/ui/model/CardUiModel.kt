package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card

@Parcelize
data class CardUiModel(
    val number: CardNumberUiModel = CardNumberUiModel(),
    val expirationDate: CardExpirationDateUiModel = CardExpirationDateUiModel(),
    val ownerName: OwnerNameUiModel = OwnerNameUiModel(),
    val password: PasswordUiModel = PasswordUiModel(),
    val bank: BankTypeUiModel = BankTypeUiModel.NOT_SELECTED,
) : Parcelable {
    fun toDomain() =
        Card(
            number = number.toDomain(),
            expirationDate = expirationDate.toDomain(),
            ownerName = ownerName.toDomain(),
            password = password.toDomain(),
            bank = bank.toDomain(),
        )
}

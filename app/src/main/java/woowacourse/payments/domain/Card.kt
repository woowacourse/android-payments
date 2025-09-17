package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.addcard.BankType

@Parcelize
data class Card(
    val number: CardNumber = CardNumber(),
    val expirationDate: CardExpirationDate = CardExpirationDate(),
    val ownerName: OwnerName = OwnerName(),
    val password: Password = Password(),
    val bank: BankType = BankType.NOT_SELECTED,
) : Parcelable {
    fun isValid(): Boolean = number.isValid() && expirationDate.isValid() && password.isValid() && bank != BankType.NOT_SELECTED
}

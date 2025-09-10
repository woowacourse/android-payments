package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val number: CardNumber = CardNumber(),
    val expirationDate: CardExpirationDate = CardExpirationDate(),
    val ownerName: OwnerName = OwnerName(),
    val password: Password = Password(),
) : Parcelable{
    fun isValid() :Boolean{
        return number.isValid() && expirationDate.isValid() && password.isValid()
    }
}

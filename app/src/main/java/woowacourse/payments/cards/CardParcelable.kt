package woowacourse.payments.cards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password

@Parcelize
data class CardParcelable(
    val cardNumber: String,
    val expiredMonth: Int,
    val expiredYear: Int,
    val ownerName: String,
    val password: String,
    val cardCompany: String,
) : Parcelable {
    fun toDomainOrNull(): Card? {
        val expiredDate =
            ExpiredDate.of(
                month = expiredMonth,
                year = expiredYear,
            ) ?: return null

        return Card(
            cardNumber = CardNumber(cardNumber),
            expiredDate = expiredDate,
            ownerName = OwnerName(ownerName),
            password = Password(password),
            cardCompany = CardCompany.valueOf(cardCompany),
        )
    }
}

fun Card.toParcelable(): CardParcelable =
    CardParcelable(
        cardNumber = cardNumber.numbers,
        expiredMonth = expiredDate.month,
        expiredYear = expiredDate.year,
        ownerName = ownerName.name,
        password = password.password,
        cardCompany = cardCompany.name,
    )

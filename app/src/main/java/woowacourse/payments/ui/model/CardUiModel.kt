package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import java.time.YearMonth

@Parcelize
data class CardUiModel(
    val cardCompanyUiModel: CardCompanyUiModel,
    val number: String,
    val expiredDate: YearMonth,
    val ownerName: String,
    val password: String,
) : Parcelable

fun CardUiModel.toDomain(): Card {
    return Card(
        cardCompany = cardCompanyUiModel.toDomain(),
        number = CardNumber(number),
        expiredDate = ExpiredDate(expiredDate),
        ownerName = OwnerName(ownerName),
        password = Password(password)
    )
}
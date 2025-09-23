package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Parcelize
data class CardUiModel(
    val cardCompanyUiModel: CardCompanyUiModel = CardCompanyUiModel.Default,
    val number: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
) : Parcelable

fun CardUiModel.toDomain(): Card {
    return Card(
        cardCompany = cardCompanyUiModel.toDomain(),
        number = CardNumber(number),
        expiredDate = ExpiredDate(
            YearMonth.parse(
                expiredDate,
                DateTimeFormatter.ofPattern("MM/yy")
            )
        ),
        ownerName = OwnerName(ownerName),
        password = Password(password)
    )
}

fun Card.toUiModel(): CardUiModel {
    return CardUiModel(
        cardCompanyUiModel = cardCompany.toUiModel(),
        number = number.value,
        expiredDate = expiredDate.toString(),
        ownerName = ownerName.value ?: "",
        password = password.value
    )
}
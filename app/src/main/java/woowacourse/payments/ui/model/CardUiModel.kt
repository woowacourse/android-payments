package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.model.Card
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.ui.text.CardNumberFormatter
import woowacourse.payments.ui.text.ExpirationDateFormatter

@Parcelize
data class CardUiModel(
    val cardCompany: CardCompanyUiModel,
    val cardNumber: String,
    val expirationDate: String,
    val userName: String?,
    val password: String,
) : Parcelable {
    companion object {
        val EMPTY =
            CardUiModel(
                cardNumber = "",
                expirationDate = "",
                userName = "",
                password = "",
                bankType = CardCompanyType.NOT_SELECTED,
            )
    }
}

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        cardNumber = CardNumberFormatter.formatAndMask(cardNumber),
        expirationDate = ExpirationDateFormatter.format(expirationDate),
        userName = userName.value,
        password = password.value,
        bankType = this.type,
    )

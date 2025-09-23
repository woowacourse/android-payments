package woowacourse.payments.ui.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.model.Card
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.ui.text.CardNumberFormatter
import woowacourse.payments.ui.text.ExpirationDateFormatter

@Parcelize
data class CardUiModel(
    val cardCompany: CardCompanyUiModel,
    val cardNumberRaw: String,
    val expirationDateRaw: String,
    val userName: String?,
    val password: String,
) : Parcelable {
    val cardNumberMasked: String
        get() = CardNumberFormatter.formatAndMask(cardNumberRaw)

    val expirationFormatted: String
        get() = ExpirationDateFormatter.format(expirationDateRaw)

    companion object {
        val EMPTY =
            CardUiModel(
                cardCompany = CardCompanyType.NOT_SELECTED.toUiModel(),
                cardNumberRaw = "",
                expirationDateRaw = "",
                userName = null,
                password = "",
            )
    }
}

@SuppressLint("DefaultLocale")
fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        cardCompany = type.toUiModel(),
        cardNumberRaw = cardNumber.value,
        expirationDateRaw =
            String.format(
                "%02d%02d",
                expirationDate.value.monthValue,
                expirationDate.value.year % 100,
            ),
        userName = userName.value,
        password = password.value,
    )

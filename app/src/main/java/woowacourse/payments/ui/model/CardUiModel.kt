package woowacourse.payments.ui.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.model.Card
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.ui.text.CardNumberFormatter
import woowacourse.payments.ui.text.ExpirationDateFormatter
import java.util.UUID

@Parcelize
data class CardUiModel(
    val id: String,
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
        const val UNASSIGNED_ID: String = ""
        val EMPTY =
            CardUiModel(
                id = UNASSIGNED_ID,
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
        id = UUID.randomUUID().toString(),
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

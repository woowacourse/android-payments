package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import woowacourse.payments.ui.format.ExpirationDateFormat
import java.time.YearMonth

@Parcelize
data class CardUiModel(
    val id: Long = System.currentTimeMillis(),
    val cardNumber: String = "",
    val expirationDate: String = "",
    val cardholderName: String = "",
    val passcode: String = "",
    val cardCompany: CardCompanyUiModel = CardCompany.NONE.toUiModel(),
) : Parcelable {
    fun toCardOrNull(): Card? =
        runCatching {
            val yearMonth: YearMonth =
                YearMonth.parse(expirationDate, ExpirationDateFormat.formatPattern)
            Card(
                id = id,
                cardNumber = CardNumber(cardNumber),
                expirationDate = ExpirationDate(yearMonth),
                cardholderName = CardholderName(cardholderName),
                passcode = Passcode(passcode),
                cardCompany = cardCompany.cardCompany,
            )
        }.getOrNull()
}

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        id = id,
        cardNumber = cardNumber.value,
        expirationDate = expirationDate.value.format(ExpirationDateFormat.formatPattern),
        cardholderName = cardholderName.value,
        passcode = passcode.value,
        cardCompany = cardCompany.toUiModel(),
    )

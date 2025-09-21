package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import woowacourse.payments.ui.format.CardNumberFormat
import woowacourse.payments.ui.format.ExpirationDateFormat
import java.time.YearMonth

@Parcelize
data class CardUiModel(
    val cardNumber: String,
    val expirationDate: String,
    val cardholderName: String,
    val passcode: String,
    val cardCompany: CardCompanyUiModel,
) : Parcelable {
    fun toCardOrNull(): Card? =
        runCatching {
            val yearMonth: YearMonth =
                YearMonth.parse(expirationDate, ExpirationDateFormat.formatPattern)
            Card(
                CardNumber(cardNumber),
                ExpirationDate(yearMonth),
                CardholderName(cardholderName),
                Passcode(passcode),
                cardCompany.company,
            )
        }.getOrNull()

    companion object {
        val EMPTY = CardUiModel("", "", "", "", CardCompany.NONE.toUiModel())
    }
}

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        cardNumber = CardNumberFormat.formatted(cardNumber),
        expirationDate = ExpirationDateFormat.formatted(expirationDate),
        cardholderName = cardholderName.value,
        passcode = passcode.value,
        cardCompany = cardCompany.toUiModel(),
    )

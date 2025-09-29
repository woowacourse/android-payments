package woowacourse.payments.ui.newcard.state.holder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardExpiryValidator
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.toBankType
import woowacourse.payments.ui.newcard.state.NewCardContentUiState
import woowacourse.payments.ui.utils.ext.toErrorResourceId
import java.time.YearMonth
import kotlin.random.Random

class NewCardContentStateHolder(
    initial: NewCardContentUiState = NewCardContentUiState(),
) {
    var cardCreateState by mutableStateOf(initial)
        private set

    val hasBank get() = cardCreateState.bankUiModel != null

    fun newCard(cardId: Long?): Card =
        cardCreateState.run {
            Card(
                requireNotNull(bankUiModel?.toBankType()),
                cardNumber,
                expiryDate,
                ownerName,
                password,
                cardId ?: Random.nextLong(1, 10000),
            )
        }

    fun updateCardBank(bankUiModel: BankUiModel) {
        cardCreateState = cardCreateState.copy(bankUiModel = bankUiModel)
    }

    fun updateCardNumber(cardNumber: String) {
        val value = cardNumber.trim()
        if (!isCardNumberAcceptable(value)) return
        cardCreateState = cardCreateState.copy(cardNumber = value)
    }

    fun updateExpiryDate(expiryDate: String) {
        val value = expiryDate.trim()
        if (!isExpiryDateAcceptable(value)) return
        val error = validateExpiryDate(value)
        cardCreateState =
            cardCreateState.copy(
                expiryDate = value,
                expiryDateErrorTextRes = error?.toErrorResourceId(),
            )
    }

    fun updateOwnerName(ownerName: String) {
        val value = ownerName.trim()
        if (!isOwnerNameAcceptable(value)) return
        cardCreateState = cardCreateState.copy(ownerName = value)
    }

    fun updatePassword(password: String) {
        val value = password.trim()
        if (!isPasswordAcceptable(value)) return
        cardCreateState = cardCreateState.copy(password = value)
    }

    private fun isCardNumberAcceptable(number: String): Boolean = number.length <= CARD_NUMBERS_MAX && number.isDigitsOnly()

    private fun isExpiryDateAcceptable(expiry: String): Boolean = expiry.length <= CARD_EXPIRY_DATE_MAX && expiry.isDigitsOnly()

    private fun isOwnerNameAcceptable(name: String): Boolean = name.length <= CARD_OWNER_NAME_MAX

    private fun isPasswordAcceptable(password: String): Boolean = password.length <= CARD_PASSWORD_MAX && password.isDigitsOnly()

    private fun validateExpiryDate(expiry: String): CardExpiryValidator? {
        val digits = expiry.filter(Char::isDigit)

        if (digits.length != 4) return null
        val mm = digits.substring(0, 2).toIntOrNull() ?: return CardExpiryValidator.InvalidMonth
        val yy = digits.substring(2, 4).toIntOrNull() ?: return CardExpiryValidator.InvalidYear
        if (mm !in 1..12) return CardExpiryValidator.InvalidMonth

        val now = YearMonth.now()
        val exp = YearMonth.of(2000 + yy, mm)
        if (exp.isBefore(now)) return CardExpiryValidator.Expired
        return null
    }

    companion object {
        private const val CARD_NUMBERS_MAX = 16
        private const val CARD_EXPIRY_DATE_MAX = 4
        const val CARD_OWNER_NAME_MAX = 30
        private const val CARD_PASSWORD_MAX = 4
    }
}

package woowacourse.payments.ui.newcard

import androidx.annotation.StringRes
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import woowacourse.payments.domain.CardExpiryValidator
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.model.NewCardUiState
import woowacourse.payments.ui.utils.ext.toErrorResourceId
import java.time.YearMonth
import kotlin.random.Random

class NewCardStateHolderSaver : Saver<NewCardStateHolder, NewCardUiState> {
    override fun SaverScope.save(value: NewCardStateHolder): NewCardUiState? =
        value.cardCreateState

    override fun restore(value: NewCardUiState): NewCardStateHolder? =
        NewCardStateHolder(value)
}

class NewCardStateHolder(
    initial: NewCardUiState = NewCardUiState(),
) {
    var cardCreateState by mutableStateOf(initial)
        private set

    val isCardCreatable: Boolean by derivedStateOf {
        hasBankType &&
                cardCreateState.run {
                    isCardNumberCreatable(cardNumber) &&
                            isExpiryDateCreatable(
                                expiryDate,
                                expiryDateErrorTextRes,
                            ) &&
                            isOwnerNameCreatable(ownerName) &&
                            isPasswordCreatable(password)
                }
    }


    val hasBankType get() = cardCreateState.bankUiModel != null

    fun newCard(cardId: Long? = null): CardUiModel =
        cardCreateState.run {
            CardUiModel(
                requireNotNull(bankUiModel),
                cardNumber,
                expiryDate,
                ownerName,
                password,
                cardId ?: Random.nextLong(1, 10000)
            )
        }

    fun updateCardInfo(cardUiModel: CardUiModel) {
        val error = validateExpiryDate(cardUiModel.cardExpiry)
        cardCreateState = cardCreateState.copy(
            bankUiModel = cardUiModel.bankUiModel,
            cardNumber = cardUiModel.cardNumbers,
            expiryDate = cardUiModel.cardExpiry,
            ownerName = cardUiModel.ownerName,
            password = cardUiModel.password,
            expiryDateErrorTextRes = error?.toErrorResourceId(),
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
        cardCreateState = cardCreateState.copy(
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

    private fun isCardNumberAcceptable(number: String): Boolean =
        number.length <= CARD_NUMBERS_MAX && number.isDigitsOnly()

    private fun isExpiryDateAcceptable(expiry: String): Boolean =
        expiry.length <= CARD_EXPIRY_DATE_MAX && expiry.isDigitsOnly()

    private fun isOwnerNameAcceptable(name: String): Boolean = name.length <= CARD_OWNER_NAME_MAX

    private fun isPasswordAcceptable(password: String): Boolean =
        password.length <= CARD_PASSWORD_MAX && password.isDigitsOnly()

    private fun isCardNumberCreatable(number: String): Boolean =
        number.length == CARD_NUMBERS_MAX && number.isDigitsOnly()

    private fun isExpiryDateCreatable(
        expiry: String,
        @StringRes errorRes: Int?,
    ): Boolean =
        expiry.length == CARD_EXPIRY_DATE_MAX &&
                expiry.isDigitsOnly() &&
                errorRes == null

    private fun isOwnerNameCreatable(name: String): Boolean = name.length <= CARD_OWNER_NAME_MAX

    private fun isPasswordCreatable(password: String): Boolean =
        password.length == CARD_PASSWORD_MAX && password.isDigitsOnly()

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

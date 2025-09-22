package woowacourse.payments.ui.registercard

import android.os.Parcelable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.BankViewType
import woowacourse.payments.ui.toBankViewType
import woowacourse.payments.ui.toYearMonth
import woowacourse.payments.ui.toYearMonthString

@Parcelize
class CardInputFieldStateHolder : Parcelable {
    @IgnoredOnParcel
    var cardNumber by mutableStateOf("")
        private set

    @IgnoredOnParcel
    var expiryDate by mutableStateOf("")
        private set

    @IgnoredOnParcel
    var cardOwner by mutableStateOf("")
        private set

    @IgnoredOnParcel
    var password by mutableStateOf("")
        private set

    @IgnoredOnParcel
    var selectedBankViewType by mutableStateOf(BankViewType.NONE)
        private set

    @IgnoredOnParcel
    val isCardNumberError by derivedStateOf {
        cardNumber.isNotEmpty() && cardNumber.length != CARD_NUMBER_MAX_LENGTH
    }

    @IgnoredOnParcel
    val isExpiryDateError by derivedStateOf {
        expiryDate.isNotEmpty() && expiryDate.length != EXPIRY_DATE_MAX_LENGTH
    }

    @IgnoredOnParcel
    val isCardOwnerError by derivedStateOf {
        cardOwner.length > CARD_OWNER_MAX_LENGTH
    }

    @IgnoredOnParcel
    val isPasswordError by derivedStateOf {
        password.isNotEmpty() && password.length != PASSWORD_MAX_LENGTH
    }

    @IgnoredOnParcel
    val canSave by derivedStateOf {
        cardBeforeEdit?.let { card ->
            cardNumber != card.cardNumber ||
                expiryDate.toYearMonth() != card.expiryDate ||
                cardOwner != (card.cardOwner ?: "") ||
                password != card.password ||
                selectedBankViewType != card.bankType.toBankViewType()
        } ?: false
    }

    @IgnoredOnParcel
    private var cardBeforeEdit: Card? by mutableStateOf(null)

    fun onCardNumberChange(newValue: String) {
        val filteredText = newValue.filter { it.isDigit() }
        if (filteredText.length <= CARD_NUMBER_MAX_LENGTH) cardNumber = filteredText
    }

    fun onExpiryDateChange(newValue: String) {
        val filteredText = newValue.filter { it.isDigit() }
        if (filteredText.length <= EXPIRY_DATE_MAX_LENGTH) expiryDate = filteredText
    }

    fun onCardOwnerChange(newValue: String) {
        if (newValue.length <= CARD_OWNER_MAX_LENGTH) cardOwner = newValue
    }

    fun onPasswordChange(newValue: String) {
        val filteredText = newValue.filter { it.isDigit() }
        if (filteredText.length <= PASSWORD_MAX_LENGTH) password = filteredText
    }

    fun onSelectedBankViewTypeChange(newValue: BankViewType) {
        selectedBankViewType = newValue
    }

    fun setupRegisteredCardInfo(card: Card) {
        cardBeforeEdit = card
        onCardNumberChange(card.cardNumber)
        onExpiryDateChange(card.expiryDate.toYearMonthString())
        onCardOwnerChange(card.cardOwner ?: "")
        onPasswordChange(card.password)
        onSelectedBankViewTypeChange(card.bankType.toBankViewType())
    }

    companion object {
        private const val CARD_NUMBER_MAX_LENGTH: Int = 16
        private const val EXPIRY_DATE_MAX_LENGTH: Int = 4
        private const val CARD_OWNER_MAX_LENGTH: Int = 30
        private const val PASSWORD_MAX_LENGTH: Int = 4
    }
}

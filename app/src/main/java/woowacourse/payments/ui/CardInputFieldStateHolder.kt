package woowacourse.payments.ui

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.Card

class CardInputFieldStateHolder {
    var cardNumber by mutableStateOf("")
        private set

    var expiryDate by mutableStateOf("")
        private set

    var cardOwner by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var selectedBankViewType by mutableStateOf(BankViewType.NONE)
        private set

    val isCardNumberError by derivedStateOf {
        cardNumber.isNotEmpty() && cardNumber.length != CARD_NUMBER_MAX_LENGTH
    }

    val isExpiryDateError by derivedStateOf {
        expiryDate.isNotEmpty() && (expiryDate.length != EXPIRY_DATE_MAX_LENGTH || expiryDate.toYearMonth() == null)
    }

    val isCardOwnerError by derivedStateOf {
        cardOwner.length > CARD_OWNER_MAX_LENGTH
    }

    val isPasswordError by derivedStateOf {
        password.isNotEmpty() && password.length != PASSWORD_MAX_LENGTH
    }

    val canSave by derivedStateOf {
        isEdited && !isCardNumberError && !isExpiryDateError && !isCardOwnerError && !isPasswordError
    }

    private val isEdited by derivedStateOf {
        val originalCard = cardBeforeEdit
        if (originalCard == null) {
            true
        } else {
            val isCardNumberChanged = cardNumber != originalCard.cardNumber
            val isExpiryDateChanged = expiryDate.toYearMonth() != originalCard.expiryDate
            val isCardOwnerChanged = cardOwner != originalCard.cardOwner.orEmpty()
            val isPasswordChanged = password != originalCard.password
            val isBankTypeChanged = selectedBankViewType != originalCard.bankType.toBankViewType()

            isCardNumberChanged || isExpiryDateChanged || isCardOwnerChanged || isPasswordChanged || isBankTypeChanged
        }
    }

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
        onCardOwnerChange(card.cardOwner ?: cardOwner)
        onPasswordChange(card.password)
        onSelectedBankViewTypeChange(card.bankType.toBankViewType())
    }

    companion object {
        private const val CARD_NUMBER_MAX_LENGTH: Int = 16
        private const val EXPIRY_DATE_MAX_LENGTH: Int = 4
        private const val CARD_OWNER_MAX_LENGTH: Int = 30
        private const val PASSWORD_MAX_LENGTH: Int = 4

        val Saver: Saver<CardInputFieldStateHolder, Map<String, Any>> =
            Saver(
                save = { state ->
                    mapOf(
                        "cardNumber" to state.cardNumber,
                        "expiryDate" to state.expiryDate,
                        "cardOwner" to state.cardOwner,
                        "password" to state.password,
                        "selectedBankViewType" to state.selectedBankViewType.name,
                    )
                },
                restore = { map ->
                    CardInputFieldStateHolder().apply {
                        onCardNumberChange(map["cardNumber"] as String)
                        onExpiryDateChange(map["expiryDate"] as String)
                        onCardOwnerChange(map["cardOwner"] as String)
                        onPasswordChange(map["password"] as String)
                        onSelectedBankViewTypeChange(BankViewType.valueOf(map["selectedBankViewType"] as String))
                    }
                },
            )
    }
}

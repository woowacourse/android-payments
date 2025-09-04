package woowacourse.payments.ui.addcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.model.CardInfo

class CardInfoUiState(
    cardNumber: String = "",
    expireDate: String = "",
    ownerName: String = "",
    password: String = "",
) {
    var cardNumber by mutableStateOf(CardInfo.formatCardNumber(cardNumber))
        private set
    var expireDate by mutableStateOf(CardInfo.formatExpireDate(expireDate))
        private set
    var ownerName by mutableStateOf(CardInfo.formatOwnerName(ownerName))
        private set
    var password by mutableStateOf(CardInfo.formatPassword(password))
        private set
    var isExpirationDateValid by mutableStateOf(checkIfMonthCompleted())
        private set

    fun onValueChanged(
        cardNumber: String = this.cardNumber,
        expireDate: String = this.expireDate,
        ownerName: String = this.ownerName,
        password: String = this.password
    ) {
        this.cardNumber = CardInfo.formatCardNumber(cardNumber)
        this.expireDate = CardInfo.formatExpireDate(expireDate)
        this.ownerName = CardInfo.formatOwnerName(ownerName)
        this.password = CardInfo.formatPassword(password)
        this.isExpirationDateValid = checkIfMonthCompleted()
    }

    private fun checkIfMonthCompleted(): Boolean {
        return if (password.length >= 2) {
            CardInfo.checkIsValidMonth(password)
        } else true
    }
    companion object {
        const val OWNER_NAME_MAX_SIZE = CardInfo.OWNER_NAME_MAX_SIZE
    }
}



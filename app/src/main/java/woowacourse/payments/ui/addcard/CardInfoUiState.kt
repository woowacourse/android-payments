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
    private var cardInfo by mutableStateOf(CardInfo(
        cardNumber = cardNumber,
        expireDate = expireDate,
        ownerName = ownerName,
        password = password
    ))
    var cardNumber by mutableStateOf(cardInfo.cardNumber)
        private set
    var expireDate by mutableStateOf(cardInfo.expireDate)
        private set
    var ownerName by mutableStateOf(cardInfo.ownerName)
        private set
    var password by mutableStateOf(cardInfo.password)
        private set
    var isExpirationDateValid by mutableStateOf(cardInfo.isExpirationDateValid())
        private set

    fun onValueChanged(
        cardNumber: String = this.cardNumber,
        expireDate: String = this.expireDate,
        ownerName: String = this.ownerName,
        password: String = this.password
    ) {
        val newCardInfo = CardInfo(cardNumber, expireDate, ownerName, password)
        this.cardNumber = newCardInfo.cardNumber
        this.expireDate = newCardInfo.expireDate
        this.ownerName = newCardInfo.ownerName
        this.password = newCardInfo.password
        this.isExpirationDateValid = newCardInfo.isExpirationDateValid()
        this.cardInfo = newCardInfo
    }

    companion object {
        const val OWNER_NAME_MAX_SIZE = CardInfo.OWNER_NAME_MAX_SIZE
    }
}
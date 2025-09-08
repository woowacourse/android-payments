package woowacourse.payments.ui.addcard

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize
import woowacourse.payments.model.CardInfo

@Parcelize
data class CardInfoUiState(
    private var _cardNumber: String = "",
    private var _expireDate: String = "",
    private var _ownerName: String = "",
    private var _password: String = "",
) : Parcelable {
    var cardNumber by mutableStateOf(CardInfo.formatCardNumber(_cardNumber))
        private set
    var expireDate by mutableStateOf(CardInfo.formatExpireDate(_expireDate))
        private set
    var ownerName by mutableStateOf(CardInfo.formatOwnerName(_ownerName))
        private set
    var password by mutableStateOf(CardInfo.formatPassword(_password))
        private set
    var isExpirationDateValid by mutableStateOf(checkIfMonthCompleted())
        private set


    fun isComplete():Boolean {
        val instance = CardInfo.createOrNull(
            cardNumber = cardNumber,
            expireDate = expireDate,
            ownerName = ownerName,
            password = password
        )
        _cardNumber = cardNumber
        _expireDate = expireDate
        _ownerName = ownerName
        _password = password
        return instance != null
    }

    fun updateCardInfo(
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
        return if (expireDate.length >= 2) {
            CardInfo.checkIsValidMonth(expireDate)
        } else true
    }
    companion object {
        const val OWNER_NAME_MAX_SIZE = CardInfo.OWNER_NAME_MAX_SIZE
    }
}



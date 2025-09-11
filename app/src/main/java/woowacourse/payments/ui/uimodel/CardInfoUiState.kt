package woowacourse.payments.ui.uimodel

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

    fun updateCardInfo(
        cardNumber: String = this.cardNumber,
        expireDate: String = this.expireDate,
        ownerName: String = this.ownerName,
        password: String = this.password,
    ) {
        this.cardNumber = CardInfo.formatCardNumber(cardNumber)
        this.expireDate = CardInfo.formatExpireDate(expireDate)
        this.ownerName = CardInfo.formatOwnerName(ownerName)
        this.password = CardInfo.formatPassword(password)
        this.isExpirationDateValid = checkIfMonthCompleted()
        _cardNumber = this.cardNumber
        _expireDate = this.expireDate
        _ownerName = this.ownerName
        _password = this.password
    }

    private fun checkIfMonthCompleted(): Boolean =
        if (expireDate.length >= 2) {
            CardInfo.checkIsValidMonth(expireDate)
        } else {
            true
        }
}

fun CardInfoUiState.isComplete(): Boolean {
    val instance =
        CardInfo.createOrNull(
            cardNumber = cardNumber,
            expireDate = expireDate,
            ownerName = ownerName,
            password = password,
        )
    return instance != null
}

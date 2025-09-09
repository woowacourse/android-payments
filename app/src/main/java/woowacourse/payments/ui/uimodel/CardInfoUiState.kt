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
    var cardNumber by mutableStateOf(CardInfo.Companion.formatCardNumber(_cardNumber))
        private set
    var expireDate by mutableStateOf(CardInfo.Companion.formatExpireDate(_expireDate))
        private set
    var ownerName by mutableStateOf(CardInfo.Companion.formatOwnerName(_ownerName))
        private set
    var password by mutableStateOf(CardInfo.Companion.formatPassword(_password))
        private set
    var isExpirationDateValid by mutableStateOf(checkIfMonthCompleted())
        private set

    fun updateCardInfo(
        cardNumber: String = this.cardNumber,
        expireDate: String = this.expireDate,
        ownerName: String = this.ownerName,
        password: String = this.password
    ) {
        this.cardNumber = CardInfo.Companion.formatCardNumber(cardNumber)
        this.expireDate = CardInfo.Companion.formatExpireDate(expireDate)
        this.ownerName = CardInfo.Companion.formatOwnerName(ownerName)
        this.password = CardInfo.Companion.formatPassword(password)
        this.isExpirationDateValid = checkIfMonthCompleted()
        _cardNumber = this.cardNumber
        _expireDate = this.expireDate
        _ownerName = this.ownerName
        _password = this.password
    }

    private fun checkIfMonthCompleted(): Boolean {
        return if (expireDate.length >= 2) {
            CardInfo.Companion.checkIsValidMonth(expireDate)
        } else true
    }

    companion object {
        const val OWNER_NAME_MAX_SIZE = CardInfo.Companion.OWNER_NAME_MAX_SIZE
    }
}

fun CardInfoUiState.isComplete(): Boolean {
    val instance = CardInfo.createOrNull(
        cardNumber = cardNumber,
        expireDate = expireDate,
        ownerName = ownerName,
        password = password
    )
    return instance != null
}

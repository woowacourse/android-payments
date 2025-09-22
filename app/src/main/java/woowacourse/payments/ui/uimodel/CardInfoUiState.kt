package woowacourse.payments.ui.uimodel

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.model.CardInfo

@Parcelize
data class CardInfoUiState(
    private var cardInfoUiModel: CardInfoUiModel = CardInfoUiModel(),
) : Parcelable {
    var cardNumber by mutableStateOf(CardInfo.formatCardNumber(cardInfoUiModel.cardNumber))
        private set
    var expireDate by mutableStateOf(CardInfo.formatExpireDate(cardInfoUiModel.expireDate))
        private set
    var ownerName by mutableStateOf(CardInfo.formatOwnerName(cardInfoUiModel.ownerName))
        private set
    var password by mutableStateOf(CardInfo.formatPassword(cardInfoUiModel.password))
        private set
    var isExpirationDateValid by mutableStateOf(CardInfo.checkIsValidMonth(expireDate))
        private set
    var vendor by mutableStateOf(cardInfoUiModel.vendor)
        private set

    fun updateCardInfo(
        cardNumber: String = this.cardNumber,
        expireDate: String = this.expireDate,
        ownerName: String = this.ownerName,
        password: String = this.password,
        vendor: VendorUiModel? = this.vendor,
    ) {
        this.cardNumber = CardInfo.formatCardNumber(cardNumber)
        this.expireDate = CardInfo.formatExpireDate(expireDate)
        this.ownerName = CardInfo.formatOwnerName(ownerName)
        this.password = CardInfo.formatPassword(password)
        this.isExpirationDateValid = CardInfo.checkIsValidMonth(expireDate)
        this.vendor = vendor
        cardInfoUiModel = CardInfoUiModel(cardNumber, expireDate, ownerName, password, vendor)
    }
}

fun CardInfoUiState.isComplete(): Boolean {
    val instance =
        CardInfo.createOrNull(
            cardNumber = cardNumber,
            expireDate = expireDate,
            ownerName = ownerName,
            password = password,
            vendor = vendor?.vendor,
        )
    return instance != null
}

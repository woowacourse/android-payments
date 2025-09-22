package woowacourse.payments.ui.cardRegister

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardValidator
import woowacourse.payments.ui.common.model.CardCompanyUiType
import woowacourse.payments.ui.common.model.CardUiModel

@Stable
class CardRegisterState(
    initialCardNumber: String = "",
    initialExpiredDate: String = "",
    initialOwnerName: String = "",
    initialPassword: String = "",
    initialIsShowingBottomSheet: Boolean = true,
    initialSelectedCardCompany: CardCompanyUiType = CardCompanyUiType.NOT_SELECTED,
) {
    var cardNumber by mutableStateOf(initialCardNumber)
        private set

    var expiredDate by mutableStateOf(initialExpiredDate)
        private set

    var ownerName by mutableStateOf(initialOwnerName)
        private set

    var password by mutableStateOf(initialPassword)
        private set

    var cardCompany by mutableStateOf(initialSelectedCardCompany)
        private set

    var isShowingBottomSheet by mutableStateOf(initialIsShowingBottomSheet)
        private set

    val cardUiModel: CardUiModel
        get() =
            CardUiModel(
                number = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
                password = password,
                cardCompany = cardCompany,
            )

    fun updateCardNumber(newCardNumber: String) {
        cardNumber = newCardNumber
    }

    fun updateExpiredDate(newExpiredDate: String) {
        expiredDate = newExpiredDate
    }

    fun updateOwnerName(newOwnerName: String) {
        ownerName = newOwnerName
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun updateCardCompany(newCardCompany: CardCompanyUiType) {
        cardCompany = newCardCompany
    }

    fun hideBottomSheet() {
        isShowingBottomSheet = false
    }

    fun isShowingCardNumberError(): Boolean = cardNumber.isNotEmpty() && !CardValidator.isValidNumber(cardNumber)

    fun isShowingExpiredDateError(): Boolean = expiredDate.isNotEmpty() && !CardValidator.isValidExpiredDate(expiredDate)

    fun isShowingOwnerNameError(): Boolean = password.isNotEmpty() && !CardValidator.isValidPassword(password)

    fun isValid(): Boolean = CardValidator.isValidCard(cardNumber, expiredDate, password)

    companion object {
        val Saver: Saver<CardRegisterState, *> =
            listSaver(
                save = { state ->
                    listOf(
                        state.cardNumber,
                        state.expiredDate,
                        state.ownerName,
                        state.password,
                        state.isShowingBottomSheet,
                        state.cardCompany.ordinal,
                    )
                },
                restore = { list ->
                    CardRegisterState(
                        initialCardNumber = list[0] as String,
                        initialExpiredDate = list[1] as String,
                        initialOwnerName = list[2] as String,
                        initialPassword = list[3] as String,
                        initialIsShowingBottomSheet = list[4] as Boolean,
                        initialSelectedCardCompany = CardCompanyUiType.entries[list[5] as Int],
                    )
                },
            )
    }
}

package woowacourse.payments.ui.addcard

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.toUiModel

class AddCardScreenUiStateHolder {
    val cardNumber: MutableState<String> = mutableStateOf("")
    val expirationDate: MutableState<String> = mutableStateOf("")
    val cardholderName: MutableState<String> = mutableStateOf("")
    val passcode: MutableState<String> = mutableStateOf("")
    val cardCompany: MutableState<CardCompanyUiModel> = mutableStateOf(CardCompany.NONE.toUiModel())

    val isCardNumberError: MutableState<Boolean> = mutableStateOf(false)
    val isExpirationDateError: MutableState<Boolean> = mutableStateOf(false)
    val isPasscodeError: MutableState<Boolean> = mutableStateOf(false)

    val isError: Boolean get() = isCardNumberError.value || isExpirationDateError.value || isPasscodeError.value

    fun checkEmptyFields() {
        if (cardNumber.value.isEmpty()) isCardNumberError.value = true
        if (expirationDate.value.isEmpty()) isExpirationDateError.value = true
        if (passcode.value.isEmpty()) isPasscodeError.value = true
    }
}

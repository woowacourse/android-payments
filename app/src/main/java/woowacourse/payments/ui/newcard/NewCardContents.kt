package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.newcard.textfields.CardHolderNameTextField
import woowacourse.payments.ui.newcard.textfields.CardNumberTextField
import woowacourse.payments.ui.newcard.textfields.ExpirationDateTextField
import woowacourse.payments.ui.newcard.textfields.PasscodeTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Suppress("ktlint:standard:function-naming")
@Composable
fun NewCardContents(
    onSaveSuccess: () -> Unit = {},
    onSaveFailure: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    val cardNumber: MutableState<String> = remember { mutableStateOf("") }
    val expirationDate: MutableState<String> = remember { mutableStateOf("") }
    val cardholderName: MutableState<String> = remember { mutableStateOf("") }
    val passcode: MutableState<String> = remember { mutableStateOf("") }

    val isCardNumberError: MutableState<Boolean> = remember { mutableStateOf(false) }
    val isExpirationDateError: MutableState<Boolean> = remember { mutableStateOf(false) }
    val isPasscodeError: MutableState<Boolean> = remember { mutableStateOf(false) }

    fun isError(): Boolean = isCardNumberError.value || isExpirationDateError.value || isPasscodeError.value

    fun checkEmptyFields() {
        if (cardNumber.value.isEmpty()) isCardNumberError.value = true
        if (expirationDate.value.isEmpty()) isExpirationDateError.value = true
        if (passcode.value.isEmpty()) isPasscodeError.value = true
    }

    fun resetFields() {
        cardNumber.value = ""
        expirationDate.value = ""
        cardholderName.value = ""
        passcode.value = ""
    }

    fun saveNewCard() {
        checkEmptyFields()
        if (isError()) {
            onSaveFailure()
        } else {
            resetFields()
            onSaveSuccess()
        }
    }

    AndroidpaymentsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                NewCardTopBar(
                    onBackClick = onBackClick,
                    onSaveClick = { saveNewCard() },
                )
            },
        ) { innerPadding: PaddingValues ->
            Column(Modifier.fillMaxSize().padding(innerPadding)) {
                PaymentCard(Modifier.align(Alignment.CenterHorizontally).padding(vertical = 30.dp))

                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    CardNumberTextField(cardNumber, isCardNumberError)

                    ExpirationDateTextField(expirationDate, isExpirationDateError)

                    CardHolderNameTextField(cardholderName)

                    PasscodeTextField(passcode, isPasscodeError)
                }
            }
        }
    }
}

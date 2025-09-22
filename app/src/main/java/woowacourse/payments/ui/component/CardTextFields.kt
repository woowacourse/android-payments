package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.registercard.CardTextFieldStateHolder

@Composable
fun CardTextFields(
    stateHolder: CardTextFieldStateHolder,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        CardNumberInputField(
            text = stateHolder.cardNumber,
            onValueChange = { newText -> stateHolder.onCardNumberChange(newText) },
            isError = stateHolder.isCardNumberError,
        )
        Spacer(modifier = Modifier.height(30.dp))
        ExpiryDateInputField(
            text = stateHolder.expiryDate,
            onValueChange = { newText -> stateHolder.onExpiryDateChange(newText) },
            isError = stateHolder.isExpiryDateError,
        )
        Spacer(modifier = Modifier.height(30.dp))
        CardOwnerInputField(
            text = stateHolder.cardOwner,
            onValueChange = { newText -> stateHolder.onCardOwnerChange(newText) },
            isError = stateHolder.isCardOwnerError,
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordInputField(
            text = stateHolder.password,
            onValueChange = { newText -> stateHolder.onPasswordChange(newText) },
            isError = stateHolder.isPasswordError,
        )
    }
}

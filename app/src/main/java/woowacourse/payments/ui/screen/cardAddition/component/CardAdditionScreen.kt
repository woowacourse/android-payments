package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardAdditionScreen(modifier: Modifier = Modifier) {
    var cardNumber by remember { mutableStateOf("") }
    var expiredDate by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = modifier,
        topBar = {
            CardAdditionTopBar(
                onBackClick = {},
                onSaveClick = {},
            )
        },
    ) { paddingValues: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp),
        ) {
            PaymentCard(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp, bottom = 28.dp),
            )
            CardNumberTextField(
                value = cardNumber,
                onCardNumberChange = { newCardNumber: String -> cardNumber = newCardNumber },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                onComplete = { focusManager.moveFocus(FocusDirection.Next) },
                onKeyboardActionClick = { focusManager.moveFocus(FocusDirection.Next) },
            )
            ExpiredDateTextField(
                value = expiredDate,
                onDateChange = { newDate: String -> expiredDate = newDate },
                modifier =
                    Modifier
                        .padding(top = 18.dp),
                onComplete = { focusManager.moveFocus(FocusDirection.Next) },
                onKeyboardActionClick = { focusManager.moveFocus(FocusDirection.Next) },
            )
            CardOwnerNameTextField(
                value = ownerName,
                onNameChange = { newName: String -> ownerName = newName },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 18.dp),
                onKeyboardActionClick = { focusManager.moveFocus(FocusDirection.Next) },
            )
            PasswordTextField(
                value = password,
                onPasswordChange = { newPassword: String -> password = newPassword },
                onComplete = { focusManager.clearFocus() },
                onKeyboardActionClick = { focusManager.clearFocus() },
            )
        }
    }
}

@Preview
@Composable
private fun CardAdditionScreenPreview() {
    CardAdditionScreen()
}

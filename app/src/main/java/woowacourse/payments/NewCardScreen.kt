package woowacourse.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.component.CardNumberTextField
import woowacourse.payments.component.CardOwnerTextField
import woowacourse.payments.component.CardPasswordTextField
import woowacourse.payments.component.ExpireDateTextField
import woowacourse.payments.component.PaymentCard

@Composable
fun NewCardScreen(
    modifier: Modifier = Modifier,
) {
    var cardNumber by remember { mutableStateOf("") }
    var expireDate by remember { mutableStateOf("") }
    var cardOwner by remember { mutableStateOf("") }
    var cardPassword by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
    ) {
        PaymentCard(
            modifier = Modifier
                .padding(top = 14.dp)
                .align(Alignment.CenterHorizontally)
        )

        CardNumberTextField(
            cardNumber = cardNumber,
            onCardNumberChange = { cardNumber = it },
            onComplete = {
                focusManager.moveFocus(FocusDirection.Next)
            },
            maxLength = 16,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp)
        )

        ExpireDateTextField(
            expireDate = expireDate,
            onExpireDateChange = { expireDate = it },
            onComplete = {
                focusManager.moveFocus(FocusDirection.Next)
            },
            maxLength = 4,
            modifier = Modifier
                .padding(top = 18.dp)
        )

        CardOwnerTextField(
            maxLength = 30,
            ownerName = cardOwner,
            onChangeOwerName = { cardOwner = it },
            modifier = Modifier
                .padding(top = 18.dp)
        )

        CardPasswordTextField(
            maxLength = 4,
            password = cardPassword,
            onPasswordChange = { cardPassword = it },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewCardScreenPreview() {
    NewCardScreen()
}

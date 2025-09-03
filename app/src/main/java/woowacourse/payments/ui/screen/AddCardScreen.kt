package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.domain.Expired
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.component.CardNumberInput
import woowacourse.payments.ui.component.CardOwnerInput
import woowacourse.payments.ui.component.ExpiredInput
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PasswordInput
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun AddCardScreen() {
    var cardNumber by remember { mutableStateOf<CardNumber?>(null) }
    var expired by remember { mutableStateOf<Expired?>(null) }
    var cardOwner by remember { mutableStateOf<CardOwner?>(CardOwner("")) }
    var password by remember { mutableStateOf<Password?>(null) }
    var showValidationError by remember { mutableStateOf(false) }

    AndroidpaymentsTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
            ) {
                NewCardTopBar(
                    onBackClick = {},
                    onSaveClick = {
                        showValidationError = true
                        val isValid =
                            cardNumber?.isValid == true && expired?.isValid == true &&
                                cardOwner?.isValid == true && password?.isValid == true

                        if (isValid) showValidationError = false
                    },
                )

                Box(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    PaymentCard()
                }

                CardNumberInput(
                    cardNumber = cardNumber,
                    onCardNumberChange = { cardNumber = it },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 20.dp),
                    showValidationError = showValidationError,
                )

                ExpiredInput(
                    expired = expired,
                    onExpiredChange = { expired = it },
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp),
                    showValidationError = showValidationError,
                )

                CardOwnerInput(
                    cardOwner = cardOwner,
                    onOwnerChange = { cardOwner = it },
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp),
                    showValidationError = showValidationError,
                )

                PasswordInput(
                    password = password,
                    onPasswordChange = { password = it },
                    modifier = Modifier.padding(horizontal = 24.dp),
                    showValidationError = showValidationError,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddCardScreenPreview() {
    AndroidpaymentsTheme {
        AddCardScreen()
    }
}

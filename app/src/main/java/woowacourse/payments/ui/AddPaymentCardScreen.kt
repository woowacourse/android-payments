package woowacourse.payments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.model.PaymentCard
import woowacourse.payments.ui.component.CardNumberTextField
import woowacourse.payments.ui.component.ExpiryTextField
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.component.PinTextField
import woowacourse.payments.ui.component.StringTextField
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun AddPaymentCardScreen(
    onBack: () -> Unit,
    onSave: (PaymentCardUiModel) -> Unit,
) {
    var cardNumber by rememberSaveable { mutableStateOf("") }
    var expiry by rememberSaveable { mutableStateOf("") }
    var owner by rememberSaveable { mutableStateOf("") }
    var pin by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            NewCardTopBar(
                modifier = Modifier.padding(bottom = 14.dp),
                onBackClick = onBack,
                onSaveClick = {
                    PaymentCard
                        .create(
                            cardNumber = cardNumber,
                            expiry = expiry,
                            owner = owner,
                            pin = pin,
                        ).onSuccess { paymentCard ->
                            onSave(paymentCard.toUiModel())
                        }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            PaymentCard(modifier = Modifier.align(Alignment.CenterHorizontally), null)

            Spacer(modifier = Modifier.height(40.dp))

            CardNumberTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                modifier = Modifier.fillMaxWidth(),
            )

            ExpiryTextField(
                value = expiry,
                onValueChange = { expiry = it },
                modifier = Modifier.fillMaxWidth(0.6f),
            )

            StringTextField(
                modifier = Modifier.fillMaxWidth(),
                value = owner,
                onValueChange = { owner = it },
                maxLength = 30,
            )

            PinTextField(
                value = pin,
                onValueChange = { pin = it },
                modifier = Modifier.fillMaxWidth(0.6f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddPaymentCardScreenPreview() {
    AndroidpaymentsTheme {
        AddPaymentCardScreen(
            onBack = {},
            onSave = {},
        )
    }
}

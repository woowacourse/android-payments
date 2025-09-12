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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.NumberTextField
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.component.StringTextField
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.transformation.NumberVisualTransformation

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
                    val paymentCard = PaymentCard(cardNumber, expiry, owner, pin)
                    onSave(paymentCard.toUiModel())
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

            NumberTextField(
                modifier = Modifier.fillMaxWidth(),
                label = R.string.label_card_number,
                placeholder = R.string.placeholder_card_number,
                value = cardNumber,
                onValueChange = { cardNumber = it },
                maxLength = 16,
                visualTransformation =
                    RememberNumberVisualTransformation(
                        4,
                        stringResource(R.string.card_number_separator),
                    ),
            )
            NumberTextField(
                modifier = Modifier.fillMaxWidth(0.6f),
                label = R.string.label_expiry,
                placeholder = R.string.placeholder_expiry,
                value = expiry,
                onValueChange = { expiry = it },
                maxLength = 4,
                visualTransformation =
                    RememberNumberVisualTransformation(
                        2,
                        stringResource(R.string.expiry_separator),
                    ),
            )
            StringTextField(
                modifier = Modifier.fillMaxWidth(),
                value = owner,
                onValueChange = { owner = it },
                maxLength = 30,
            )
            NumberTextField(
                modifier = Modifier.fillMaxWidth(0.6f),
                label = R.string.label_pin,
                placeholder = R.string.placeholder_pin,
                value = pin,
                onValueChange = { pin = it },
                maxLength = 4,
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}

@Composable
private fun RememberNumberVisualTransformation(
    chunkSize: Int,
    separator: String,
): VisualTransformation =
    remember(chunkSize, separator) {
        NumberVisualTransformation(chunkSize, separator)
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

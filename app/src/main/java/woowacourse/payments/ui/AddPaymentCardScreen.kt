package woowacourse.payments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import woowacourse.payments.domain.model.CardNumber
import woowacourse.payments.domain.model.Expiry
import woowacourse.payments.domain.model.PaymentCard
import woowacourse.payments.domain.model.Pin
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

    val isCardNumberValid = cardNumber.isValid(CardNumber::from)
    val isExpiryValid = expiry.isValid(Expiry::from)
    val isPinValid = pin.isValid(Pin::from)

    val canSave = listOf(isCardNumberValid, isExpiryValid, isPinValid).all { it }

    val cardVisualTransformation =
        RememberNumberVisualTransformation(4, stringResource(R.string.card_number_separator))
    val expiryVisualTransformation =
        RememberNumberVisualTransformation(2, stringResource(R.string.expiry_separator))

    Scaffold(
        topBar = {
            NewCardTopBar(
                modifier = Modifier.padding(bottom = 14.dp),
                onBackClick = onBack,
                onSaveClick = {
                    if (!canSave) return@NewCardTopBar
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

            NumberTextField(
                modifier = Modifier.fillMaxWidth(),
                label = R.string.label_card_number,
                placeholder = R.string.placeholder_card_number,
                value = cardNumber,
                onValueChange = { cardNumber = it },
                maxLength = 16,
                visualTransformation = cardVisualTransformation,
                isError = cardNumber.isNotEmpty() && !isCardNumberValid,
                supportingText =
                    ErrorTextOrNull(
                        show = cardNumber.isNotEmpty() && !isCardNumberValid,
                        resId = R.string.error_card_number_invalid,
                    ),
            )
            NumberTextField(
                modifier = Modifier.fillMaxWidth(0.6f),
                label = R.string.label_expiry,
                placeholder = R.string.placeholder_expiry,
                value = expiry,
                onValueChange = { expiry = it },
                maxLength = 4,
                visualTransformation = expiryVisualTransformation,
                isError = expiry.isNotEmpty() && !isExpiryValid,
                supportingText =
                    ErrorTextOrNull(
                        show = expiry.isNotEmpty() && !isExpiryValid,
                        resId = R.string.error_expiry_invalid,
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
                isError = pin.isNotEmpty() && !isPinValid,
                supportingText =
                    ErrorTextOrNull(
                        show = pin.isNotEmpty() && !isPinValid,
                        resId = R.string.error_pin_invalid,
                    ),
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

@Composable
private fun ErrorTextOrNull(
    show: Boolean,
    resId: Int,
): (@Composable () -> Unit)? =
    if (!show) {
        null
    } else {
        { Text(text = stringResource(resId), color = MaterialTheme.colorScheme.error) }
    }

private inline fun String.isValid(validator: (String) -> Any?): Boolean = isNotEmpty() && validator(this) != null

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

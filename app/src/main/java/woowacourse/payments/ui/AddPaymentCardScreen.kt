package woowacourse.payments.ui

import android.app.Activity
import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.model.EXTRA_CARD
import woowacourse.payments.model.PaymentCard
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.NumberTextField
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.component.StringTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.transformation.NumberVisualTransformation

@Composable
fun AddPaymentCardScreen() {
    val context = LocalContext.current

    var cardNumber by rememberSaveable { mutableStateOf("") }
    var expiry by rememberSaveable { mutableStateOf("") }
    var owner by rememberSaveable { mutableStateOf("") }
    var pin by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            NewCardTopBar(
                modifier = Modifier.padding(bottom = 14.dp),
                onBackClick = { (context as? Activity)?.finish() },
                onSaveClick = {
                    val paymentCard =
                        PaymentCard(cardNumber = cardNumber, expiry = expiry, owner = owner)
                    (context as? Activity)?.apply {
                        setResult(Activity.RESULT_OK, Intent().putExtra(EXTRA_CARD, paymentCard))
                        finish()
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
                visualTransformation =
                    NumberVisualTransformation(
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
                    NumberVisualTransformation(
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

@Preview(showBackground = true)
@Composable
private fun AddPaymentCardScreenPreview() {
    AndroidpaymentsTheme {
        AddPaymentCardScreen()
    }
}

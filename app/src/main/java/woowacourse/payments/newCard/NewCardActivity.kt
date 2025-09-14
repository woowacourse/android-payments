package woowacourse.payments.newCard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import woowacourse.payments.InputMask
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardExpiry
import woowacourse.payments.domain.CardName
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.list.toUiModel
import woowacourse.payments.ui.DigitTextField
import woowacourse.payments.ui.LimitedUppercaseTextField
import woowacourse.payments.ui.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val newCardState = NewCardState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        NewCardTopBar(
                            onBackClick = {
                                finish()
                            },
                            onSaveClick = {
                                val data =
                                    Intent().apply {
                                        putExtra(
                                            "card",
                                            Card(
                                                CardNumber(newCardState.cardNumber),
                                                CardExpiry.fromString(newCardState.cardExpiry),
                                                password = CardPassword(newCardState.cardPassword),
                                                name = CardName(newCardState.cardName),
                                            ).toUiModel()
                                        )
                                    }
                                setResult(RESULT_OK, data)
                                finish()
                            },
                            isSaveEnabled = runCatching { Card(
                                CardNumber(newCardState.cardNumber),
                                CardExpiry.fromString(newCardState.cardExpiry),
                                password = CardPassword(newCardState.cardPassword),
                                name = CardName(newCardState.cardName),
                            ) }.isSuccess,
                        )
                    },
                ) { innerPadding ->
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                    ) {
                        Spacer(modifier = Modifier.height(14.dp))
                        Box(
                            modifier =
                                Modifier
                                    .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            PaymentCard()
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        DigitTextField(
                            text = newCardState.cardNumber,
                            onValueChange = { newCardState.onNumberChange(it) },
                            label = getString(R.string.card_number_label),
                            hint = "0000 - 0000 - 0000 - 0000",
                            modifier = Modifier.padding(horizontal = 24.dp),
                            maxLength = 16,
                            mask = InputMask.CardNumber,
                            errorMessage = newCardState.numberErrorMessage,
                            imeAction = ImeAction.Next,
                            isError = newCardState.isNumberError,
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        DigitTextField(
                            text = newCardState.cardExpiry,
                            onValueChange = { newCardState.onExpiryChange(it) },
                            label = getString(R.string.card_expiry_label),
                            hint = "MM / YY",
                            modifier =
                                Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(horizontal = 24.dp),
                            maxLength = 4,
                            mask = InputMask.Expiry,
                            errorMessage = newCardState.expiryErrorMessage,
                            imeAction = ImeAction.Next,
                            isError = newCardState.isExpiryError,
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        LimitedUppercaseTextField(
                            text = newCardState.cardName,
                            onValueChange = { newCardState.onNameChange(it) },
                            label = getString(R.string.card_owner_label),
                            hint = getString(R.string.card_owner_hint),
                            modifier = Modifier.padding(horizontal = 24.dp),
                            maxLength = 30,
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        DigitTextField(
                            text = newCardState.cardPassword,
                            onValueChange = { newCardState.onPasswordChange(it) },
                            label = getString(R.string.card_password_label),
                            hint = "0000",
                            modifier =
                                Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(horizontal = 24.dp),
                            maxLength = 4,
                            mask = InputMask.Password,
                            errorMessage = newCardState.passwordErrorMessage,
                            isError = newCardState.isPasswordError,
                        )
                    }
                }
            }
        }
    }
}

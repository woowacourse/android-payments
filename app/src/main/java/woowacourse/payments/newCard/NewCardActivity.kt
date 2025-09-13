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
import woowacourse.payments.list.CardUiModel
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
                val newCardStateHolder = NewCardStateHolder()

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
                                            CardUiModel(number = newCardStateHolder.cardNumber, expiry = newCardStateHolder.cardExpiry, password = newCardStateHolder.cardPassword, name = newCardStateHolder.cardName),
                                        )
                                    }
                                setResult(RESULT_OK, data)
                                finish()
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
                            text = newCardStateHolder.cardNumber,
                            onValueChange = { newCardStateHolder.cardNumber = it },
                            label = getString(R.string.card_number_label),
                            hint = "0000 - 0000 - 0000 - 0000",
                            modifier = Modifier.padding(horizontal = 24.dp),
                            maxLength = 16,
                            mask = InputMask.CardNumber,
                            errorMessage = getString(R.string.card_number_error_message),
                            imeAction = ImeAction.Next,
                            isError = newCardStateHolder.cardNumber.length < 16 && newCardStateHolder.cardNumber.isNotEmpty(),
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        DigitTextField(
                            text = newCardStateHolder.cardExpiry,
                            onValueChange = { newCardStateHolder.cardExpiry = it },
                            label = getString(R.string.card_expiry_label),
                            hint = "MM / YY",
                            modifier =
                                Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(horizontal = 24.dp),
                            maxLength = 4,
                            mask = InputMask.Expiry,
                            errorMessage = getString(R.string.card_expiry_error_message),
                            imeAction = ImeAction.Next,
                            isError = newCardStateHolder.cardExpiry.length < 4 && newCardStateHolder.cardExpiry.isNotEmpty(),
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        LimitedUppercaseTextField(
                            text = newCardStateHolder.cardName,
                            onValueChange = { newCardStateHolder.cardName = it },
                            label = getString(R.string.card_owner_label),
                            hint = getString(R.string.card_owner_hint),
                            modifier = Modifier.padding(horizontal = 24.dp),
                            maxLength = 30,
                            imeAction = ImeAction.Next,
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        DigitTextField(
                            text = newCardStateHolder.cardPassword,
                            onValueChange = { newCardStateHolder.cardPassword = it },
                            label = getString(R.string.card_password_label),
                            hint = "0000",
                            modifier =
                                Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(horizontal = 24.dp),
                            maxLength = 4,
                            mask = InputMask.Password,
                            errorMessage = getString(R.string.card_password_error_message),
                            isError = newCardStateHolder.cardPassword.length < 4 && newCardStateHolder.cardPassword.isNotEmpty(),
                        )
                    }
                }
            }
        }
    }
}

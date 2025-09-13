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
                                            Card(
                                                CardNumber(newCardStateHolder.cardNumber),
                                                CardExpiry.fromString(newCardStateHolder.cardExpiry),
                                                password = CardPassword(newCardStateHolder.cardPassword),
                                                name = CardName(newCardStateHolder.cardName),
                                            ).toUiModel()
                                        )
                                    }
                                setResult(RESULT_OK, data)
                                finish()
                            },
                            isSaveEnabled = runCatching { Card(
                                CardNumber(newCardStateHolder.cardNumber),
                                CardExpiry.fromString(newCardStateHolder.cardExpiry),
                                password = CardPassword(newCardStateHolder.cardPassword),
                                name = CardName(newCardStateHolder.cardName),
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
                            text = newCardStateHolder.cardNumber,
                            onValueChange = { newCardStateHolder.cardNumber = it },
                            label = getString(R.string.card_number_label),
                            hint = "0000 - 0000 - 0000 - 0000",
                            modifier = Modifier.padding(horizontal = 24.dp),
                            maxLength = 16,
                            mask = InputMask.CardNumber,
                            errorMessage = runCatching { CardNumber(newCardStateHolder.cardNumber) }.exceptionOrNull()?.message ?: "",
                            imeAction = ImeAction.Next,
                            isError = if (newCardStateHolder.cardNumber.isNotEmpty()) runCatching { CardNumber(newCardStateHolder.cardNumber) }.isFailure else false,
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
                            errorMessage = runCatching { CardExpiry.fromString(newCardStateHolder.cardExpiry) }.exceptionOrNull()?.message ?: "",
                            imeAction = ImeAction.Next,
                            isError = if (newCardStateHolder.cardExpiry.isNotEmpty()) runCatching { CardExpiry.fromString(newCardStateHolder.cardExpiry) }.isFailure else false,
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
                            errorMessage = runCatching { CardPassword(newCardStateHolder.cardPassword) }.exceptionOrNull()?.message ?: "",
                            isError = if (newCardStateHolder.cardPassword.isNotEmpty()) runCatching { CardPassword(newCardStateHolder.cardPassword) }.isFailure else false,
                        )
                    }
                }
            }
        }
    }
}

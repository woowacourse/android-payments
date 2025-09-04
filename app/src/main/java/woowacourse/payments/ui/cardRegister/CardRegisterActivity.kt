package woowacourse.payments.ui.cardRegister

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.cardRegister.components.CardRegisterTopBar
import woowacourse.payments.ui.cardRegister.components.PaymentCard
import woowacourse.payments.ui.cardRegister.components.PaymentTextField
import woowacourse.payments.ui.common.CreditCardVisualTransformation
import woowacourse.payments.ui.common.DateVisualTransformation
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                var cardNumber by remember { mutableStateOf("") }
                var expiredDate by remember { mutableStateOf("") }
                var ownerName by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                Scaffold(
                    topBar = {
                        CardRegisterTopBar(
                            onBackClick = { }, // TODO: 뒤로가기 이동
                            onSaveClick = { }, // TODO: 카드 저장
                        )
                    },
                    modifier = Modifier.Companion.fillMaxSize(),
                ) { innerPadding ->
                    Column(
                        modifier =
                            Modifier.Companion
                                .fillMaxWidth()
                                .padding(innerPadding),
                    ) {
                        PaymentCard(
                            modifier =
                                Modifier.Companion
                                    .padding(top = 14.dp)
                                    .align(Alignment.Companion.CenterHorizontally),
                        )
                        PaymentTextField(
                            text = cardNumber,
                            onValueChanged = { cardNumber = it },
                            label = stringResource(R.string.card_number_label),
                            hint = stringResource(R.string.card_number_place_holder),
                            maxLength = 16,
                            onlyDigits = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Companion.Number),
                            visualTransformation = CreditCardVisualTransformation(),
                            modifier =
                                Modifier.Companion
                                    .fillMaxWidth()
                                    .padding(top = 40.dp)
                                    .padding(horizontal = 24.dp),
                        )
                        PaymentTextField(
                            text = expiredDate,
                            onValueChanged = { expiredDate = it },
                            label = stringResource(R.string.expired_date_label),
                            hint = stringResource(R.string.expired_date_place_holder),
                            maxLength = 4,
                            onlyDigits = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Companion.Number),
                            visualTransformation = DateVisualTransformation(),
                            modifier =
                                Modifier.Companion
                                    .fillMaxWidth(0.5f)
                                    .padding(top = 30.dp)
                                    .padding(horizontal = 24.dp),
                        )
                        PaymentTextField(
                            text = ownerName,
                            onValueChanged = { ownerName = it },
                            label = stringResource(R.string.card_owner_label),
                            hint = stringResource(R.string.card_owner_place_holder),
                            supportingText = {
                                Text(
                                    text =
                                        stringResource(
                                            R.string.card_owner_supporting_text,
                                            ownerName.length,
                                        ),
                                    textAlign = TextAlign.Companion.End,
                                    modifier = Modifier.Companion.fillMaxWidth(),
                                )
                            },
                            maxLength = 30,
                            modifier =
                                Modifier.Companion
                                    .fillMaxWidth()
                                    .padding(top = 30.dp)
                                    .padding(horizontal = 24.dp),
                        )
                        PaymentTextField(
                            text = password,
                            onValueChanged = { password = it },
                            label = stringResource(R.string.card_password_label),
                            hint = stringResource(R.string.card_password_place_holder),
                            maxLength = 4,
                            onlyDigits = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Companion.Number),
                            visualTransformation = PasswordVisualTransformation(),
                            modifier =
                                Modifier.Companion
                                    .fillMaxWidth(0.5f)
                                    .padding(top = 10.dp)
                                    .padding(horizontal = 24.dp),
                        )
                    }
                }
            }
        }
    }
}

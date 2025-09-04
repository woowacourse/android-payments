package woowacourse.payments

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.common.CreditCardVisualTransformation
import woowacourse.payments.ui.common.DateVisualTransformation
import woowacourse.payments.ui.components.NewCardTopBar
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.components.PaymentTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class MainActivity : ComponentActivity() {
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
                        NewCardTopBar(
                            onBackClick = { }, // TODO: 뒤로가기 이동
                            onSaveClick = { }, // TODO: 카드 저장
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(innerPadding),
                    ) {
                        PaymentCard(
                            modifier = Modifier
                                .padding(top = 14.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        PaymentTextField(
                            text = cardNumber,
                            onValueChanged = { cardNumber = it },
                            label = "카드 번호",
                            hint = "0000-0000-0000-0000",
                            maxLength = 16,
                            onlyDigits = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            visualTransformation = CreditCardVisualTransformation(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp)
                                .padding(horizontal = 24.dp),
                        )
                        PaymentTextField(
                            text = expiredDate,
                            onValueChanged = { expiredDate = it },
                            label = "만료일",
                            hint = "MM / YY",
                            maxLength = 4,
                            onlyDigits = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            visualTransformation = DateVisualTransformation(),
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(top = 30.dp)
                                .padding(horizontal = 24.dp)
                        )
                        PaymentTextField(
                            text = ownerName,
                            onValueChanged = { ownerName = it },
                            label = "카드 소유자 이름(선택)",
                            hint = "카드에 표시된 이름을 입력하세요.",
                            supportingText = {
                                Text(
                                    text = "${ownerName.length}/30",
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            maxLength = 30,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp)
                                .padding(horizontal = 24.dp)
                        )
                        PaymentTextField(
                            text = password,
                            onValueChanged = { password = it },
                            label = "비밀번호",
                            hint = "0000",
                            maxLength = 4,
                            onlyDigits = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(top = 10.dp)
                                .padding(horizontal = 24.dp)
                        )
                    }
                }
            }
        }
    }
}

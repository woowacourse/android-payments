package woowacourse.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.components.NewCardTopBar
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
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
                        PaymentsCardNumberTextField(
                            text = "1",
                            label = "카드 번호",
                            hint = "1234-5678-8910-1112",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp)
                                .padding(horizontal = 24.dp),
                        )
                        PaymentsExpiredDateTextField(
                            text = "",
                            label = "만료일",
                            hint = "MM / YY",
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(top = 40.dp)
                                .padding(horizontal = 24.dp)
                        )
                        PaymentsCardOwnerTextField(
                            text = "",
                            label = "카드 소유자 이름(선택)",
                            hint = "카드에 표시된 이름을 입력하세요.",
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        PaymentsExpiredDateTextField(
                            text = "",
                            label = "비밀번호",
                            hint = "0000",
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

@Composable
fun PaymentsCardNumberTextField(
    text: String,
    label: String,
    hint: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { "" },
        label = { Text(label) },
        placeholder = { Text(hint) },
        enabled = true,
        modifier = modifier,
    )
}

@Composable
fun PaymentsExpiredDateTextField(
    text: String,
    label: String,
    hint: String,
    isHide: Boolean = false,
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        placeholder = { Text(hint) },
        enabled = true,
        singleLine = true,
        visualTransformation = if (isHide) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = modifier,
    )
}

@Composable
fun PaymentsCardOwnerTextField(
    text: String?,
    label: String,
    hint: String,
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        placeholder = { Text(hint) },
        enabled = true,
        modifier = modifier
            .padding(top = 40.dp)
            .padding(horizontal = 24.dp),
        supportingText = {
            Text(
                text = "${text.length}/30",
                textAlign = TextAlign.End,
                modifier = modifier
                    .fillMaxWidth()
            )
        }
    )
}
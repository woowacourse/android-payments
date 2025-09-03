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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
                            label = "카드 번호",
                            hint = "0000-0000-0000-0000",
                            maxLength = 16,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp)
                                .padding(horizontal = 24.dp),
                        )
                        PaymentTextField(
                            label = "만료일",
                            hint = "MM / YY",
                            maxLength = 4,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(top = 30.dp)
                                .padding(horizontal = 24.dp)
                        )
                        PaymentTextField(
                            label = "카드 소유자 이름(선택)",
                            hint = "카드에 표시된 이름을 입력하세요.",
                            maxLength = 30,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                        )
                        PaymentTextField(
                            label = "비밀번호",
                            hint = "0000",
                            isTextHide = true,
                            maxLength = 4,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

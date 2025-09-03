package woowacourse.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        NewCardTopBar(
                            onBackClick = { },
                            onSaveClick = {},
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        PaymentCard(
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        DigitFieldText(
                            label = "카드 번호",
                            hint = "0000 - 0000 - 0000 - 0000",
                            maxLength = 16,
                            mask = InputMask.CardNumber,
                            )
                        DigitFieldText(label = "만료일",
                            hint = "MM / YY",
                            fraction = 0.5f,
                            maxLength = 4,
                            mask = InputMask.Expiry,
                            )
                        LimitedTextField(
                            label = "카드 소유자 이름(선택)",
                            hint = "카드에 표시된 이름을 입력하세요.",
                            maxLength = 30,
                        )
                        DigitFieldText(label = "비밀번호",
                            hint = "0000",
                            fraction = 0.5f,
                            maxLength = 4,
                            mask = InputMask.Password,
                            )
                    }
                }
            }
        }
    }
}

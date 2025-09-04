package woowacourse.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
                        Spacer(modifier = Modifier.height(14.dp))
                        PaymentCard(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        DigitFieldText(
                            label = "카드 번호",
                            hint = "0000 - 0000 - 0000 - 0000",
                            maxLength = 16,
                            mask = InputMask.CardNumber,
                            errorMessage = "카드 번호는 16자입니다."
                            )
                        Spacer(modifier = Modifier.height(30.dp))
                        DigitFieldText(label = "만료일",
                            hint = "MM / YY",
                            fraction = 0.5f,
                            maxLength = 4,
                            mask = InputMask.Expiry,
                            errorMessage = "유효하지 않은 만료일입니다."
                            )
                        Spacer(modifier = Modifier.height(30.dp))
                        LimitedTextField(
                            label = "카드 소유자 이름(선택)",
                            hint = "카드에 표시된 이름을 입력하세요.",
                            maxLength = 30,
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        DigitFieldText(label = "비밀번호",
                            hint = "0000",
                            fraction = 0.5f,
                            maxLength = 4,
                            mask = InputMask.Password,
                            errorMessage = "비밀번호는 4자입니다."
                            )
                    }
                }
            }
        }
    }
}

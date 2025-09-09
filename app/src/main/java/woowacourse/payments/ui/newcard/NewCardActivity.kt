package woowacourse.payments.ui.newcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    onSaved = { /* TODO: 저장 기능 추가 */ },
                    onFinish = { finish() },
                )
            }
        }
    }
}

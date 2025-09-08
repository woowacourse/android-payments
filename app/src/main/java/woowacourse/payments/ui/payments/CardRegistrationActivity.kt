package woowacourse.payments.ui.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.payments.screen.CardRegistrationScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardRegistrationScreen()
            }
        }
    }
}
package woowacourse.payments.ui.cardwallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme

class CardWalletActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardWalletScreen()
            }
        }
    }
}

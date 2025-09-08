package woowacourse.payments.ui.allcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import woowacourse.payments.ui.allcards.component.AllCardsTopbar
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AllCardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(
                    topBar = {
                        AllCardsTopbar()
                    }
                ) { padding ->
                    AllCardsScreen(padding)
                }
            }
        }
    }
}
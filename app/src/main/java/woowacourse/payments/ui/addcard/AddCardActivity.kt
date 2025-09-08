package woowacourse.payments.ui.addcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import woowacourse.payments.ui.addcard.component.AddCardTopbar
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                Scaffold(
                    topBar = {
                        AddCardTopbar()
                    }
                ) { padding ->
                    AddCardScreen(padding)
                }
            }
        }
    }
}
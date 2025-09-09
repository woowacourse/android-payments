package woowacourse.payments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.screen.AddCardScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                AddCardScreen(
                    onBackPressed = {},
                    onAddCard = { cardInfo ->
                        val resultIntent =
                            Intent().apply {
                                putExtra("cardInfo", cardInfo)
                            }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    },
                )
            }
        }
    }
}

package woowacourse.payments.ui.screen.addCard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                AddCardScreen(
                    onBackPressed = { finish() },
                    onCardSaved = { cardUiModel ->
                        val resultIntent =
                            Intent().apply {
                                putExtra("new_card", cardUiModel)
                            }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    },
                )
            }
        }
    }
}

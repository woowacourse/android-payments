package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
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
                    onSaved = { card ->
                        val intent = Intent().putExtra(EXTRA_NEW_CARD_RESULT, card)
                        setResult(RESULT_OK, intent)
                        finish()
                    },
                    onFinish = { finish() },
                )
            }
        }
    }

    companion object {
        const val EXTRA_NEW_CARD_RESULT: String = "EXTRA_NEW_CARD_RESULT"

        fun newIntent(context: Context): Intent = Intent(context, NewCardActivity::class.java)
    }
}

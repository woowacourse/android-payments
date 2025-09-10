package woowacourse.payments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class NewCardScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    onBackClick = { finish() },
                    onSaveClick = { paymentCard ->
                        setResult(RESULT_OK, Intent().putExtra(ADD_NEW_CARD, paymentCard))
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        const val ADD_NEW_CARD = "ADD_NEW_CARD"

        fun newIntent(context: Context): Intent {
            return Intent(context, NewCardScreenActivity::class.java)
        }
    }
}

package woowacourse.payments.ui.newcard

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    onBackClick = { finish() },
                    onSaveClick = { card: Card ->
                        val intent =
                            Intent().apply { putExtra(INTENT_NEW_CARD_KEY, card) }
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        const val INTENT_NEW_CARD_KEY = "new_card"

        fun newIntent(context: Context): Intent = Intent(context, NewCardActivity::class.java)
    }
}

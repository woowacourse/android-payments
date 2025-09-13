package woowacourse.payments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.newcard.NewCardScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.util.showShortToast

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    onBackClick = { finish() },
                    onSaveClick = { card ->
                        val resultIntent = Intent().putExtra(KEY_CARD, card)
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    },
                    onCardSaveFailed = {
                        showShortToast(getString(R.string.card_save_failed))
                    },
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, NewCardActivity::class.java)

        const val KEY_CARD = "key_card"
    }
}

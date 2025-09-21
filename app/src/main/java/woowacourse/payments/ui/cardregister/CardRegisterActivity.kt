package woowacourse.payments.ui.cardregister

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidpaymentsTheme {
                CardRegisterScreen(
                    onBackClick = { finish() },
                    onSaveClick = { card -> navigateToCards(card) },
                )
            }
        }
    }

    private fun navigateToCards(card: Card) {
        val intent =
            Intent().apply {
                putExtra(KEY_NEW_CARD, card)
            }

        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val KEY_NEW_CARD: String = "new_card"

        fun newIntent(context: Context): Intent = Intent(context, CardRegisterActivity::class.java)
    }
}

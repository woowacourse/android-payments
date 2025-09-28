package woowacourse.payments.ui.cardform

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.getParcelableExtraCompat
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val registeredCard =
            intent.getParcelableExtraCompat<Card>(KEY_CARD_TO_EDIT)

        setContent {
            AndroidpaymentsTheme {
                CardFormScreen(
                    onBackClick = { finish() },
                    onSaveClick = { card -> navigateToCards(card) },
                    card = registeredCard,
                )
            }
        }
    }

    private fun navigateToCards(card: Card) {
        val intent =
            Intent().apply {
                putExtra(KEY_CARD_TO_SAVE, card)
            }

        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val KEY_CARD_TO_SAVE: String = "card_to_save"
        private const val KEY_CARD_TO_EDIT: String = "card_to_edit"

        fun newIntent(
            context: Context,
            card: Card? = null,
        ): Intent =
            Intent(context, CardFormActivity::class.java).apply {
                card?.let { putExtra(KEY_CARD_TO_EDIT, it) }
            }
    }
}

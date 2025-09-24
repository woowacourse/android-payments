package woowacourse.payments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.model.Card
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.screen.AddCardScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val initial = parseInitial(intent)

        setContent {
            AndroidpaymentsTheme {
                AddCardScreen(
                    onBackPressed = { finish() },
                    onAddCard = { card -> setCardResultAndFinish(card) },
                    initialShowSheet = initial == null,
                    initialCard = initial,
                )
            }
        }
    }

    private fun setCardResultAndFinish(card: Card) {
        val resultIntent =
            Intent().apply {
                putExtra(EXTRA_CARD_RESULT, card.toUiModel())
            }
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    companion object {
        private const val EXTRA_CARD_RESULT = "EXTRA_CARD_RESULT"
        private const val EXTRA_INITIAL_CARD = "EXTRA_INITIAL_CARD"

        fun newIntent(context: Context): Intent = Intent(context, AddCardActivity::class.java)

        fun newIntent(
            context: Context,
            initial: CardUiModel,
        ): Intent =
            Intent(context, AddCardActivity::class.java).apply {
                putExtra(EXTRA_INITIAL_CARD, initial)
            }

        fun parseInitial(intent: Intent?): CardUiModel? =
            intent?.let {
                IntentCompat.getParcelableExtra(it, EXTRA_INITIAL_CARD)
            }

        fun parseResult(data: Intent?): CardUiModel? =
            data?.let {
                IntentCompat.getParcelableExtra(it, EXTRA_CARD_RESULT)
            }
    }
}

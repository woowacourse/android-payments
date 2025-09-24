package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.newcard.model.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.getParcelableCompat

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val card: CardUiModel? = intent.getParcelableCompat(INTENT_CARD_KEY)
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    card = card,
                    companies = CardCompany.entries.map(CardCompany::toUiModel),
                    onBackClick = { finish() },
                    onSaveClick = { newCard: CardUiModel ->
                        if (card == null) saveCard(newCard) else editCard(newCard)
                        showToast(getString(R.string.card_added_message))
                        finish()
                    },
                )
            }
        }
    }

    private fun saveCard(card: CardUiModel) {
        val intent = Intent().apply { putExtra(INTENT_CARD_KEY, card) }
        setResult(RESULT_CODE_SAVE, intent)
    }

    private fun editCard(card: CardUiModel) {
        val intent = Intent().apply { putExtra(INTENT_CARD_KEY, card) }
        setResult(RESULT_CODE_EDIT, intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val RESULT_CODE_SAVE = 0
        const val RESULT_CODE_EDIT = 1
        const val INTENT_CARD_KEY = "card"

        fun newIntent(
            context: Context,
            card: CardUiModel?,
        ): Intent =
            Intent(context, NewCardActivity::class.java).apply {
                putExtra(INTENT_CARD_KEY, card)
            }
    }
}

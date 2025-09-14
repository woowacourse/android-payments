package woowacourse.payments.ui.card.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidpaymentsTheme {
                RegisterCardScreen(
                    onCardSaved = { newCardUiModel ->
                        setResult(RESULT_OK, newCardIntent(newCardUiModel))
                        finish()
                    },
                    onBackClick = { finish() },
                )
            }
        }
    }

    companion object {
        const val EXTRA_NEW_CARD = "new_card_ui_model"

        fun newIntent(context: Context): Intent = Intent(context, CardRegisterActivity::class.java)

        fun newCardIntent(newCard: CardUiModel): Intent =
            Intent().apply {
                putExtra(EXTRA_NEW_CARD, newCard)
            }
    }
}

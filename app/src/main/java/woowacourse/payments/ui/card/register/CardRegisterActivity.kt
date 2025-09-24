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

        val cardToEdit = intent.getParcelableExtra<CardUiModel>(EXTRA_EDIT_CARD)

        setContent {
            AndroidpaymentsTheme {
                RegisterCardScreen(
                    cardToEdit = cardToEdit,
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
        const val EXTRA_EDIT_CARD = "edit_card_ui_model"

        fun newIntent(context: Context): Intent = Intent(context, CardRegisterActivity::class.java)

        fun editCardIntent(
            context: Context,
            card: CardUiModel,
        ): Intent =
            Intent(context, CardRegisterActivity::class.java).apply {
                putExtra(EXTRA_EDIT_CARD, card)
            }

        fun newCardIntent(newCard: CardUiModel): Intent =
            Intent().apply {
                putExtra(EXTRA_NEW_CARD, newCard)
            }
    }
}

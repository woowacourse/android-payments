package woowacourse.payments.ui.screen.cards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardsScreen()
            }
        }
    }

    companion object {
        const val EXTRA_CARDS_REGISTER_NEW_CARD = "EXTRA_CARDS_REGISTER_NEW_CARD"

        fun newIntent(
            context: Context,
            newCard: CardUiModel,
        ): Intent =
            Intent(context, CardsActivity::class.java).apply {
                putExtra(EXTRA_CARDS_REGISTER_NEW_CARD, newCard)
            }
    }
}

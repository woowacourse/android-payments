package woowacourse.payments.ui.screen.cards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.cards.component.CardsScreen
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
        const val EXTRA_CARD = "EXTRA_CARD"

        fun newIntent(
            context: Context,
            card: CardUiModel,
        ): Intent =
            Intent(context, CardsActivity::class.java).apply {
                putExtra(EXTRA_CARD, card)
            }
    }
}

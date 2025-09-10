package woowacourse.payments.ui.payments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class PaymentCardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                PaymentCardsScreen()
            }
        }
    }

    companion object {
        const val EXTRA_PAYMENT_CARDS_REGISTER_NEW_CARD = "EXTRA_PAYMENT_CARDS_REGISTER_NEW_CARD"

        fun newIntent(
            context: Context,
            newCard: CardUiModel,
        ): Intent =
            Intent(context, PaymentCardsActivity::class.java).apply {
                putExtra(EXTRA_PAYMENT_CARDS_REGISTER_NEW_CARD, newCard)
            }
    }
}

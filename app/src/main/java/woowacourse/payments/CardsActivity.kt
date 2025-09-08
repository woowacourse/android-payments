package woowacourse.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.cards.CardsScreen
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    private val cards: List<Card> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardsScreen(paymentCards = cards)
            }
        }
    }
}

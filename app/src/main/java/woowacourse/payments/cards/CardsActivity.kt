package woowacourse.payments.cards

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import woowacourse.payments.Card
import woowacourse.payments.PaymentsApplication
import woowacourse.payments.R
import woowacourse.payments.cards.component.CardsScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    private val viewModel: CardsViewModel by lazy { (application as PaymentsApplication).cardsViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val cards: SnapshotStateList<Card> = remember { mutableStateListOf() }
            LaunchedEffect(Unit) {
                setUpObservers(cards)
            }

            AndroidpaymentsTheme {
                CardsScreen(
                    cards = cards,
                    addCard = viewModel::addCard,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

    private fun setUpObservers(cards: SnapshotStateList<Card>) {
        viewModel.cards.observe(this) { newCards: List<Card> ->
            cards.clear()
            cards.addAll(newCards)
        }

        viewModel.event.observe(this) { event: CardsUiEvent ->
            when (event) {
                CardsUiEvent.AddCardSuccess -> {
                    Toast
                        .makeText(
                            this,
                            getString(R.string.cards_add_card_success_message),
                            Toast.LENGTH_SHORT,
                        ).show()
                }
            }
        }
    }
}

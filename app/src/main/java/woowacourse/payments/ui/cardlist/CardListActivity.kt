package woowacourse.payments.ui.cardlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cards: MutableList<Card> = remember { mutableStateListOf() }
                CardListScreen(
                    cards = cards,
                    onAddClick = ::navigateToNewCard,
                )
            }
        }
    }

    private fun navigateToNewCard() {
        val intent: Intent = NewCardActivity.newIntent(this)
        startActivity(intent)
    }
}

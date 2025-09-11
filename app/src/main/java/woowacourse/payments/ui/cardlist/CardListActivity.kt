package woowacourse.payments.ui.cardlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cards: MutableList<CardUiModel> = rememberSaveable { mutableStateListOf() }
                CardListScreen(
                    cards = cards,
                    onCardAdded = { cards.add(it) },
                )
            }
        }
    }
}

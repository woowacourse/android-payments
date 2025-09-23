package woowacourse.payments.ui.cardlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.collections.immutable.toImmutableList
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cardlist.composable.CardListScreen

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var cards by remember { mutableStateOf(emptyList<Card>()) }
            var currentIndex by remember { mutableIntStateOf(0) }
            CardListScreen(
                onEditCard = { card ->
                    cards =
                        cards.mapIndexed { index, oldCard ->
                            if (index == currentIndex) {
                                card
                            } else {
                                oldCard
                            }
                        }
                },
                cards = cards.toImmutableList(),
                onAddCard = { card -> cards = cards + card },
                onChangeIndex = { index -> currentIndex = index },
            )
        }
    }
}

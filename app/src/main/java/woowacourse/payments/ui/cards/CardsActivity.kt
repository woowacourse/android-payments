package woowacourse.payments.ui.cards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.cards.multi.MultiCardsScreen
import woowacourse.payments.ui.cards.non.NonCardsScreen
import woowacourse.payments.ui.cards.single.SingleCardScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var screen by remember { mutableStateOf<CardsScreen>(CardsScreen.Non) }
            AndroidpaymentsTheme {
                when (val currentScreen = screen) {
                    CardsScreen.Non -> NonCardsScreen({ screen = it })
                    is CardsScreen.Single -> SingleCardScreen({ screen = it }, currentScreen.card)
                    is CardsScreen.Multi -> MultiCardsScreen(currentScreen.cards)
                }
            }
        }
    }

    companion object {
        const val NEW_CARD_KEY = "new_card_key"

        fun intent(context: Context, cardAction: CardAction) = Intent(
            context,
            CardsActivity::class.java
        ).putExtra(NEW_CARD_KEY, cardAction)
    }
}

package woowacourse.payments.cards

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import woowacourse.payments.R
import woowacourse.payments.cards.component.CardsScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val stateHolder =
                rememberSaveable(saver = CardsStateHolder.Saver) { CardsStateHolder() }
            var event by remember { mutableStateOf<CardsUiEvent?>(null) }

            LaunchedEffect(event) {
                handleEvent(event)
                event = null
            }

            AndroidpaymentsTheme {
                CardsScreen(
                    state = stateHolder.uiState,
                    addCard = { card ->
                        stateHolder.addCard(card)
                        event = CardsUiEvent.AddCardSuccess
                    },
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

    private fun handleEvent(event: CardsUiEvent?) {
        when (event) {
            CardsUiEvent.AddCardSuccess -> {
                Toast
                    .makeText(
                        this,
                        getString(R.string.cards_add_card_success_message),
                        Toast.LENGTH_SHORT,
                    ).show()
            }

            null -> Unit
        }
    }
}

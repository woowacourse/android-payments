package woowacourse.payments.view.cards

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import woowacourse.payments.view.cardaddition.CardAdditionActivity
import woowacourse.payments.view.cardediting.CardEditingActivity
import woowacourse.payments.view.cards.component.CardsScreen
import woowacourse.payments.view.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val stateHolder: CardsStateHolder = rememberCardsStateHolder()
                val state: CardsUiState = stateHolder.uiState
                val cardsUpdateLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                        if (result.resultCode == RESULT_OK) {
                            stateHolder.fetchCards()
                        }
                    }
                val onUiEvent: (CardsUiEvent) -> Unit = onUiEvent(cardsUpdateLauncher)

                CardsScreen(
                    state = state,
                    onUiEvent = onUiEvent,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

    private fun onUiEvent(cardsUpdateLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>): (CardsUiEvent) -> Unit =
        { event: CardsUiEvent ->
            when (event) {
                CardsUiEvent.NavigateToCardAddition -> {
                    cardsUpdateLauncher.launch(
                        Intent(this, CardAdditionActivity::class.java),
                    )
                }

                is CardsUiEvent.NavigateToCardEditing -> {
                    cardsUpdateLauncher.launch(
                        CardEditingActivity.intent(this, event.card),
                    )
                }
            }
        }
}

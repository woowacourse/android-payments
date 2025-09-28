package woowacourse.payments.view.cardaddition

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import woowacourse.payments.R
import woowacourse.payments.view.cardaddition.component.CardAdditionScreen
import woowacourse.payments.view.ui.theme.AndroidpaymentsTheme

class CardAdditionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val stateHolder: CardAdditionStateHolder = rememberCardAdditionStateHolder()
                val state: CardAdditionUiState = stateHolder.uiState
                val event: CardAdditionUiEvent? = stateHolder.uiEvent
                val onUiEvent: (CardAdditionUiEvent) -> Unit = onUiEvent(stateHolder)

                LaunchedEffect(event) {
                    event?.let { event ->
                        onUiEvent(event)
                        stateHolder.clearEvent()
                    }
                }

                CardAdditionScreen(
                    state = state,
                    onUiEvent = onUiEvent,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

    private fun onUiEvent(stateHolder: CardAdditionStateHolder): (CardAdditionUiEvent) -> Unit =
        { event: CardAdditionUiEvent ->
            when (event) {
                CardAdditionUiEvent.AddCardSuccess -> {
                    Toast
                        .makeText(
                            this,
                            getString(R.string.card_addition_add_card_success_message),
                            Toast.LENGTH_SHORT,
                        ).show()
                    setResult(RESULT_OK)
                    finish()
                }

                CardAdditionUiEvent.AddCardFailure -> {
                    Toast
                        .makeText(
                            this,
                            getString(R.string.card_addition_add_card_failure_message),
                            Toast.LENGTH_SHORT,
                        ).show()
                }

                CardAdditionUiEvent.AddCard -> {
                    stateHolder.addCard()
                }

                CardAdditionUiEvent.NavigateBack -> {
                    finish()
                }

                is CardAdditionUiEvent.UpdateBankType -> {
                    stateHolder.updateBankType(event.bankType)
                }

                is CardAdditionUiEvent.UpdateCardNumber -> {
                    stateHolder.updateCardNumber(event.cardNumber)
                }

                is CardAdditionUiEvent.UpdateExpiredDate -> {
                    stateHolder.updateExpiredDate(event.expiredDate)
                }

                is CardAdditionUiEvent.UpdateHolder -> {
                    stateHolder.updateHolder(event.holder)
                }

                is CardAdditionUiEvent.UpdatePassword -> {
                    stateHolder.updatePassword(event.password)
                }
            }
        }
}

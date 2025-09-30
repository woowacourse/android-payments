package woowacourse.payments.view.cardediting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import woowacourse.payments.R
import woowacourse.payments.view.cardediting.component.CardEditingScreen
import woowacourse.payments.view.getParcelableExtraCompat
import woowacourse.payments.view.ui.model.CardUiModel
import woowacourse.payments.view.ui.theme.AndroidpaymentsTheme

class CardEditingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val card: CardUiModel = intent.getParcelableExtraCompat(EXTRA_CARD) ?: return finish()

        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val stateHolder: CardEditingStateHolder =
                    rememberCardEditingStateHolder(CardEditingUiState(card))
                val state: CardEditingUiState = stateHolder.uiState
                val event: CardEditingUiEvent? = stateHolder.uiEvent
                val onUiEvent: (CardEditingUiEvent) -> Unit = onUiEvent(stateHolder)

                LaunchedEffect(event) {
                    event?.let { event ->
                        onUiEvent(event)
                        stateHolder.clearEvent()
                    }
                }

                CardEditingScreen(
                    state = state,
                    onUiEvent = onUiEvent,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

    private fun onUiEvent(stateHolder: CardEditingStateHolder): (CardEditingUiEvent) -> Unit =
        { event: CardEditingUiEvent ->
            when (event) {
                CardEditingUiEvent.NavigateBack -> {
                    finish()
                }

                CardEditingUiEvent.EditCard -> {
                    stateHolder.editCard()
                }

                CardEditingUiEvent.EditCardSuccess -> {
                    Toast
                        .makeText(
                            this,
                            getString(R.string.card_addition_add_card_success_message),
                            Toast.LENGTH_SHORT,
                        ).show()
                    setResult(RESULT_OK)
                    finish()
                }

                CardEditingUiEvent.EditCardFailure -> {
                    Toast
                        .makeText(
                            this,
                            getString(R.string.card_addition_add_card_failure_message),
                            Toast.LENGTH_SHORT,
                        ).show()
                }

                is CardEditingUiEvent.UpdateBankType -> {
                    stateHolder.updateBankType(event.bankType)
                }

                is CardEditingUiEvent.UpdateCardNumber -> {
                    stateHolder.updateCardNumber(event.cardNumber)
                }

                is CardEditingUiEvent.UpdateExpiredDate -> {
                    stateHolder.updateExpiredDate(event.expiredDate)
                }

                is CardEditingUiEvent.UpdateHolder -> {
                    stateHolder.updateHolder(event.holder)
                }

                is CardEditingUiEvent.UpdatePassword -> {
                    stateHolder.updatePassword(event.password)
                }
            }
        }

    companion object {
        fun intent(
            context: Context,
            card: CardUiModel,
        ): Intent = Intent(context, CardEditingActivity::class.java).putExtra(EXTRA_CARD, card)

        private const val EXTRA_CARD = "woowacourse.payments.CARD"
    }
}

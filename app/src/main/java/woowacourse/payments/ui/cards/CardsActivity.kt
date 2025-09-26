package woowacourse.payments.ui.cards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import woowacourse.payments.R
import woowacourse.payments.ui.cardform.CardFormActivity
import woowacourse.payments.ui.cards.state.CardsViewModel
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.common.showToast
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = CardsViewModel()

            val cardFormLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                    if (activityResult.resultCode == RESULT_OK) {
                        val card: CardUiModel? =
                            activityResult.data?.getParcelableExtraCompat(EXTRA_CARD)
                        val action: CardAction? =
                            activityResult.data?.getParcelableExtraCompat(EXTRA_ACTION)
                        action?.let {
                            when (action) {
                                CardAction.Register -> {
                                    card?.let { viewModel.registrationCard(card) }
                                    showToast(messageResource = R.string.cards_screen_registration_toast)
                                }

                                is CardAction.Modify -> {
                                    card?.let { viewModel.updateCard(action.cardId, card) }
                                    showToast(messageResource = R.string.cards_screen_modify_toast)
                                }
                            }
                        }
                    }
                }

            AndroidpaymentsTheme {
                CardsScreen(
                    onRegistrationClick = {
                        val intent = CardFormActivity.newIntent(this, CardAction.Register)
                        cardFormLauncher.launch(intent)
                    },
                    onCardClick = { card: CardUiModel ->
                        val intent =
                            CardFormActivity.newIntent(
                                this,
                                CardAction.Modify(
                                    card.id ?: throw IllegalArgumentException("키 값 유실"),
                                ),
                            )
                        cardFormLauncher.launch(intent)
                    },
                    viewModel = viewModel,
                )
            }
        }
    }

    companion object {
        private const val EXTRA_CARD = "EXTRA_CARD"
        private const val EXTRA_ACTION = "EXTRA_ACTION"

        fun newIntent(
            context: Context,
            card: CardUiModel,
            action: CardAction,
        ): Intent =
            Intent(context, CardsActivity::class.java).apply {
                putExtra(EXTRA_CARD, card)
                putExtra(EXTRA_ACTION, action)
            }
    }
}

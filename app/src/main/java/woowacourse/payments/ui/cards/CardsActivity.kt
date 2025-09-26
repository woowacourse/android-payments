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
import woowacourse.payments.ui.cards.state.CardsViewModel
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.common.showToast
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.registration.CardRegistrationActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = CardsViewModel()

            val cardAddLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                    if (activityResult.resultCode == RESULT_OK) {
                        val newCard: CardUiModel? =
                            activityResult.data?.getParcelableExtraCompat(
                                EXTRA_CARDS_REGISTER_NEW_CARD,
                            )
                        newCard?.let {
                            viewModel.registrationCard(newCard)
                            showToast(messageResource = R.string.cards_screen_registration_toast)
                        }
                    }
                }

            AndroidpaymentsTheme {
                CardsScreen(
                    onRegistrationClick = {
                        val intent = CardRegistrationActivity.newIntent(this)
                        cardAddLauncher.launch(intent)
                    },
                    viewModel = viewModel,
                )
            }
        }
    }

    companion object {
        private const val EXTRA_CARDS_REGISTER_NEW_CARD = "EXTRA_CARDS_REGISTER_NEW_CARD"

        fun newIntent(
            context: Context,
            newCard: CardUiModel,
        ): Intent =
            Intent(context, CardsActivity::class.java).apply {
                putExtra(EXTRA_CARDS_REGISTER_NEW_CARD, newCard)
            }
    }
}

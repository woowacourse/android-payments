package woowacourse.payments.ui.screen.cards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import woowacourse.payments.R
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.registration.CardRegistrationActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var cardScreenUiState: CardsScreenUiState by remember {
                mutableStateOf(CardsScreenUiState())
            }

            val cardAddLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                    if (activityResult.resultCode == RESULT_OK) {
                        val newCard: CardUiModel? =
                            activityResult.data?.getParcelableExtraCompat(
                                EXTRA_CARDS_REGISTER_NEW_CARD,
                            )
                        newCard?.let {
                            cardScreenUiState = cardScreenUiState.copyWithAddCard(newCard)
                            Toast
                                .makeText(
                                    this,
                                    getString(R.string.cards_screen_registration_toast),
                                    Toast.LENGTH_SHORT,
                                ).show()
                        }
                    }
                }

            AndroidpaymentsTheme {
                CardsScreen(
                    onRegistrationClick = {
                        val intent = CardRegistrationActivity.newIntent(this)
                        cardAddLauncher.launch(intent)
                    },
                    uiState = cardScreenUiState,
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

package woowacourse.payments.ui.cards

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.getParcelableExtraCompat
import woowacourse.payments.ui.registercard.RegisterCardActivity
import woowacourse.payments.ui.registercard.RegisterCardActivity.Companion.KEY_CARD_TO_SAVE
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val stateHolder = rememberSaveable { CardsStateHolder() }
            AndroidpaymentsTheme {
                val launcher =
                    rememberLauncherForActivityResult(
                        ActivityResultContracts.StartActivityForResult(),
                    ) { activityResult ->
                        if (activityResult.resultCode == RESULT_OK) {
                            val newCard =
                                activityResult.data?.getParcelableExtraCompat<Card>(KEY_CARD_TO_SAVE)
                                    ?: return@rememberLauncherForActivityResult
                            val cardIndex =
                                stateHolder.cardsState.indexOfFirst { it.id == newCard.id }

                            if (cardIndex == INVALID_INDEX) {
                                stateHolder.cardsState.add(newCard)
                            } else {
                                stateHolder.cardsState[cardIndex] = newCard
                            }
                        }
                    }

                CardsScreen(
                    stateHolder,
                    onCardAddClick = { navigateToRegisterCard(launcher) },
                    onCardClick = { cardToEdit -> navigateToEditCard(cardToEdit, launcher) },
                )
            }
        }
    }

    private fun navigateToRegisterCard(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        val intent = RegisterCardActivity.newIntent(this)
        launcher.launch(intent)
    }

    private fun navigateToEditCard(
        card: Card,
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    ) {
        val intent = RegisterCardActivity.newIntent(this, card)
        launcher.launch(intent)
    }

    companion object {
        private const val INVALID_INDEX: Int = -1
    }
}

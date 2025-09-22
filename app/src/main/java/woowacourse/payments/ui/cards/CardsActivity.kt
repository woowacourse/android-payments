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
import woowacourse.payments.ui.editcard.EditCardActivity
import woowacourse.payments.ui.editcard.EditCardActivity.Companion.KEY_EDITED_CARD
import woowacourse.payments.ui.getParcelableExtraCompat
import woowacourse.payments.ui.registercard.RegisterCardActivity
import woowacourse.payments.ui.registercard.RegisterCardActivity.Companion.KEY_NEW_CARD
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    private var cardToEditIndex: Int = INITIAL_INDEX

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
                        updateCardsViewFromCardRegister(
                            activityResult,
                            stateHolder,
                        )
                        if (activityResult.resultCode == RESULT_OK) {
                            val editedCard =
                                activityResult.data?.getParcelableExtraCompat<Card>(
                                    KEY_EDITED_CARD,
                                ) ?: return@rememberLauncherForActivityResult

                            if (cardToEditIndex != INITIAL_INDEX) {
                                stateHolder.cardsState[cardToEditIndex] =
                                    editedCard
                            }
                        }
                    }

                CardsScreen(
                    stateHolder,
                    onCardAddClick = { navigateToCardRegister(launcher) },
                    onCardClick = { cardToEdit ->
                        navigateToCardEdit(cardToEdit, launcher)
                        cardToEditIndex = stateHolder.cardsState.indexOfFirst { it == cardToEdit }
                    },
                )
            }
        }
    }

    private fun updateCardsViewFromCardRegister(
        activityResult: ActivityResult,
        stateHolder: CardsStateHolder,
    ) {
        if (activityResult.resultCode == RESULT_OK) {
            val newCard =
                activityResult.data?.getParcelableExtraCompat<Card>(KEY_NEW_CARD)
                    ?: return
            stateHolder.cardsState.add(newCard)
        }
    }

    private fun navigateToCardRegister(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        val intent = RegisterCardActivity.newIntent(this)
        launcher.launch(intent)
    }

    private fun navigateToCardEdit(
        card: Card,
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    ) {
        val intent = EditCardActivity.newIntent(this, card)
        launcher.launch(intent)
    }

    companion object {
        private const val INITIAL_INDEX: Int = -1
    }
}

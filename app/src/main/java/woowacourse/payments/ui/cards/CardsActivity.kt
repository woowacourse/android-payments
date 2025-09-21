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
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cardregister.CardRegisterActivity
import woowacourse.payments.ui.cardregister.CardRegisterActivity.Companion.KEY_NEW_CARD
import woowacourse.payments.ui.getParcelableExtraCompat
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    private val stateHolder = CardsStateHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val launcher =
                    rememberLauncherForActivityResult(
                        ActivityResultContracts.StartActivityForResult(),
                    ) { activityResult -> updateCardsViewFromCardRegister(activityResult) }

                CardsScreen(
                    stateHolder,
                    onCardAddClick = { navigateToCardRegister(launcher) },
                )
            }
        }
    }

    private fun updateCardsViewFromCardRegister(activityResult: ActivityResult) {
        if (activityResult.resultCode == RESULT_OK) {
            val newCard =
                activityResult.data?.getParcelableExtraCompat<Card>(KEY_NEW_CARD)
                    ?: return
            stateHolder.cardsState.add(newCard)
        }
    }

    private fun navigateToCardRegister(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        val intent = CardRegisterActivity.newIntent(this)
        launcher.launch(intent)
    }
}

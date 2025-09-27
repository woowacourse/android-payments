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
import woowacourse.payments.view.ui.model.CardUiModel
import woowacourse.payments.view.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val stateHolder = rememberCardsStateHolder()

                val cardsUpdateLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                        if (result.resultCode == RESULT_OK) {
                            stateHolder.fetchCards()
                        }
                    }

                val navigateToCardAdditionActivity: () -> Unit =
                    { cardsUpdateLauncher.launch(Intent(this, CardAdditionActivity::class.java)) }

                val navigateToEditingActivity: (card: CardUiModel) -> Unit =
                    { card -> cardsUpdateLauncher.launch(CardEditingActivity.intent(this, card)) }

                CardsScreen(
                    stateHolder,
                    addCard = navigateToCardAdditionActivity,
                    editCard = navigateToEditingActivity,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

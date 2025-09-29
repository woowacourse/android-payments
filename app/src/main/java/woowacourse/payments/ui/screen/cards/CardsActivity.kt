package woowacourse.payments.ui.screen.cards

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.screen.registration.CardRegistrationActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardsScreen(
                    onRegisterCardClick = ::navigateToCardRegistration,
                    onEditCardClick = ::navigateToEditCard,
                )
            }
        }
    }

    private fun navigateToCardRegistration(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        val newIntent = CardRegistrationActivity.newIntent(this)
        launcher.launch(newIntent)
    }

    private fun navigateToEditCard(
        card: PaymentCardUiModel,
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    ) {
        val newIntent = CardRegistrationActivity.newIntent(this, card)
        launcher.launch(newIntent)
    }
}

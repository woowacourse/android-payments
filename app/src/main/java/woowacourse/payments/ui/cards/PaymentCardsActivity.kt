package woowacourse.payments.ui.cards

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import woowacourse.payments.R
import woowacourse.payments.ui.add.AddPaymentCardActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class PaymentCardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidpaymentsTheme {
                val stateHolder = rememberPaymentCardsStateHolder()

                LaunchedEffect(Unit) {
                    stateHolder.refreshFromStore()
                }

                val cardAddLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                        if (result.resultCode == RESULT_OK) {
                            stateHolder.refreshFromStore()
                            Toast
                                .makeText(
                                    this,
                                    getString(R.string.toast_card_add),
                                    Toast.LENGTH_SHORT,
                                ).show()
                        }
                    }

                PaymentCardsScreen(
                    paymentCards = stateHolder.state.cards,
                    onAddCard = { cardAddLauncher.launch(AddPaymentCardActivity.newIntent(this)) },
                    onEditCard = { cardAddLauncher.launch(AddPaymentCardActivity.newIntent(this)) },
                )
            }
        }
    }
}

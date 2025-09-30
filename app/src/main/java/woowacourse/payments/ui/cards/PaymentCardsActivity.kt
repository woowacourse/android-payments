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

                            val mode =
                                result.data?.getStringExtra(AddPaymentCardActivity.EXTRA_RESULT_MODE)

                            val messageRes =
                                when (mode) {
                                    AddPaymentCardActivity.RESULT_MODE_EDIT -> R.string.toast_card_edit
                                    AddPaymentCardActivity.RESULT_MODE_ADD -> R.string.toast_card_add
                                    else -> R.string.toast_card_add
                                }

                            Toast
                                .makeText(
                                    this,
                                    getString(messageRes),
                                    Toast.LENGTH_SHORT,
                                ).show()
                        }
                    }

                PaymentCardsScreen(
                    paymentCards = stateHolder.state.cards,
                    onAddCard = { cardAddLauncher.launch(AddPaymentCardActivity.newIntent(this)) },
                    onEditCard = { id ->
                        val intent =
                            AddPaymentCardActivity
                                .newIntent(this)
                                .putExtra(AddPaymentCardActivity.EXTRA_CARD_ID, id)
                        cardAddLauncher.launch(intent)
                    },
                )
            }
        }
    }
}

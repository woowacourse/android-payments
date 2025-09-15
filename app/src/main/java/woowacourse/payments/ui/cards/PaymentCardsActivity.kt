package woowacourse.payments.ui.cards

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import woowacourse.payments.R
import woowacourse.payments.ui.add.AddPaymentCardActivity
import woowacourse.payments.ui.common.parcelable
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class PaymentCardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidpaymentsTheme {
                val stateHolder = rememberPaymentCardsStateHolder()

                val cardAddLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                        if (result.resultCode == RESULT_OK) {
                            val newCard = result.data?.parcelable<PaymentCardUiModel>(EXTRA_CARD)
                            if (newCard != null) {
                                stateHolder.addCard(newCard)
                                Toast
                                    .makeText(
                                        this,
                                        getString(R.string.toast_card_add),
                                        Toast.LENGTH_SHORT,
                                    ).show()
                            }
                        }
                    }

                PaymentCardsScreen(
                    paymentCards = stateHolder.state.cards,
                    onAddCard = { cardAddLauncher.launch(AddPaymentCardActivity.newIntent(this)) },
                )
            }
        }
    }

    companion object {
        private const val EXTRA_CARD = "extra_card"
    }
}

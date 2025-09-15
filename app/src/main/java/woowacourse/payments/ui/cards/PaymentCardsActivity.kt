package woowacourse.payments.ui.cards

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
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
                val context = LocalContext.current
                var paymentCards by rememberSaveable { mutableStateOf(listOf<PaymentCardUiModel>()) }

                val cardAddLauncher =
                    androidx.activity.compose.rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                    ) { result ->
                        if (result.resultCode == RESULT_OK) {
                            val newCard = result.data?.parcelable<PaymentCardUiModel>(EXTRA_CARD)
                            if (newCard != null) {
                                paymentCards = paymentCards + newCard
                                Toast
                                    .makeText(
                                        context,
                                        context.getString(R.string.toast_card_add),
                                        Toast.LENGTH_SHORT,
                                    ).show()
                            }
                        }
                    }

                PaymentCardsScreen(
                    paymentCards = paymentCards,
                    onAddCard = { cardAddLauncher.launch(AddPaymentCardActivity.newIntent(context)) },
                )
            }
        }
    }

    companion object {
        private const val EXTRA_CARD = "extra_card"
    }
}

package woowacourse.payments.ui.cards

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import woowacourse.payments.R
import woowacourse.payments.ui.add.AddPaymentCardActivity
import woowacourse.payments.ui.common.parcelable
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class PaymentCardsActivity : ComponentActivity() {
    private lateinit var cardAddLauncher: ActivityResultLauncher<Intent>
    private var onCardAdded: ((PaymentCardUiModel) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        cardAddLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val newCard =
                        result.data?.parcelable<PaymentCardUiModel>(EXTRA_CARD)
                            ?: return@registerForActivityResult
                    onCardAdded?.invoke(newCard)
                    Toast
                        .makeText(
                            this,
                            getString(R.string.toast_card_add),
                            Toast.LENGTH_SHORT,
                        ).show()
                }
            }

        setContent {
            AndroidpaymentsTheme {
                var paymentCards by rememberSaveable { mutableStateOf(listOf<PaymentCardUiModel>()) }

                onCardAdded = { newCard -> paymentCards = paymentCards + newCard }

                PaymentCardsScreen(
                    paymentCards = paymentCards,
                    onAddCard = { navigateToAddPaymentCard() },
                )
            }
        }
    }

    private fun navigateToAddPaymentCard() {
        val intent = AddPaymentCardActivity.newIntent(this)
        cardAddLauncher.launch(intent)
    }

    companion object {
        private const val EXTRA_CARD = "extra_card"
    }
}

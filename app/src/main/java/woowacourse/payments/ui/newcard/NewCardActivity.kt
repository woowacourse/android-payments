package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.data.BankRepository
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.extensions.getParcelableCompat

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val initialCard = intent.getParcelableCompat<PaymentCardUiModel>(EXTRA_NEW_CARD_INITIAL_CARD)
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    banks = BankRepository.getBanks(),
                    initialCard = initialCard,
                    onBackPress = { finish() },
                    onSaved = { result ->
                        result
                            .onSuccess { paymentCard ->
                                runCatching {
                                    setResult(
                                        RESULT_OK,
                                        Intent().putExtra(EXTRA_NEW_CARD, paymentCard),
                                    )
                                    finish()
                                }.onFailure { e ->
                                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                                }
                            }.onFailure { e ->
                                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                            }
                    },
                )
            }
        }
    }

    companion object {
        const val EXTRA_NEW_CARD = "EXTRA_NEW_CARD"
        const val EXTRA_NEW_CARD_INITIAL_CARD = "EXTRA_NEW_CARD_INITIAL_CARD"

        fun newIntent(
            context: Context,
            paymentCard: PaymentCardUiModel? = null,
        ): Intent =
            Intent(context, NewCardActivity::class.java).apply {
                putExtra(EXTRA_NEW_CARD_INITIAL_CARD, paymentCard)
            }
    }
}

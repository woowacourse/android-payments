package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.data.BankRepository
import woowacourse.payments.domain.model.Bank
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.extensions.getParcelableCompat

class NewCardActivity : ComponentActivity() {
    private val newCardStateHolder = NewCardStateHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val initialCard =
            intent.getParcelableCompat<PaymentCardUiModel>(EXTRA_NEW_CARD_INITIAL_CARD)?.apply {
                updateInitialCard(this)
            }
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    banks = BankRepository.getBanks(),
                    initialCard = initialCard,
                    newCardStateHolder = newCardStateHolder,
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

    private fun updateInitialCard(initialCard: PaymentCardUiModel) {
        newCardStateHolder.updateId(initialCard.id)
        newCardStateHolder.updateCardNumber(initialCard.cardNumber.value)
        newCardStateHolder.updateCardHolder(initialCard.cardHolder.value)
        newCardStateHolder.updateBank(Bank(initialCard.bankType))
        newCardStateHolder.expirationDateUiState.onValueChanged(initialCard.expirationDate.value)
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

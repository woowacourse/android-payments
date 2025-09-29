package woowacourse.payments.ui.screen.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.os.bundleOf
import woowacourse.payments.ui.extension.getParcelableExtraCompat
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardRegistrationScreen(
                    onBackClick = ::finish,
                    onRegisteredCard = { newCard ->
                        setResult(RESULT_OK, Intent().putExtra(EXTRA_NEW_CARD, newCard))
                        finish()
                    },
                    onUpdatedCard = { updatedCard ->
                        setResult(RESULT_OK, Intent().putExtra(EXTRA_EDIT_CARD, updatedCard))
                        finish()
                    },
                    viewModel =
                        rememberCardRegistrationScreenViewModel(
                            extractEditCardFromIntent()?.let(CardRegistrationScreenUiState::from),
                        ),
                )
            }
        }
    }

    private fun extractEditCardFromIntent(): PaymentCardUiModel? = intent.getParcelableExtraCompat(EXTRA_EDIT_CARD)

    companion object {
        const val EXTRA_EDIT_CARD = "EXTRA_EDIT_CARD"
        const val EXTRA_NEW_CARD = "EXTRA_NEW_CARD"

        fun newIntent(
            context: Context,
            editCard: PaymentCardUiModel? = null,
        ): Intent =
            Intent(context, CardRegistrationActivity::class.java).putExtras(
                bundleOf(EXTRA_EDIT_CARD to editCard),
            )
    }
}

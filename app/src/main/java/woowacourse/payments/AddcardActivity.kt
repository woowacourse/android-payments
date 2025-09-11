package woowacourse.payments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.features.addcard.AddCardScreen
import woowacourse.payments.ui.mapper.CardMapper.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddcardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                AddCardScreen(
                    onNavigateBack = { finish() },
                    onNavigateSave = { card: PaymentCard ->
                        val intent =
                            Intent().apply {
                                putExtra(EXTRA_PAYMENT_CARD_UI_MODEL, card.toUiModel())
                            }
                        setResult(RESULT_OK, intent)
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        const val EXTRA_PAYMENT_CARD_UI_MODEL = "EXTRA_PAYMENT_CARD"
    }
}

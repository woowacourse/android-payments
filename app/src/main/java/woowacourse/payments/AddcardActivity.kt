package woowacourse.payments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.features.addcard.AddCardScreen
import woowacourse.payments.ui.mapper.CardMapper.toUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.getParcelableExtraCompat

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
        private const val EXTRA_PAYMENT_CARD_UI_MODEL = "EXTRA_PAYMENT_CARD"

        fun getPaymentCardUiModel(activityResult: ActivityResult): PaymentCardUiModel? {
            if (activityResult.resultCode != Activity.RESULT_OK) {
                return null
            }
            return activityResult.data?.getParcelableExtraCompat<PaymentCardUiModel>(
                EXTRA_PAYMENT_CARD_UI_MODEL,
            )
        }
    }
}

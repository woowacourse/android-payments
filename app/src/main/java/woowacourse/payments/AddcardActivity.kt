package woowacourse.payments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import woowacourse.payments.domain.card.PaymentCard
import woowacourse.payments.ui.features.cartinput.CardInputScreen
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
                CardInputScreen(
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

        fun newIntent(context: Context): Intent = Intent(context, AddcardActivity::class.java)

        fun getPaymentCardUiModel(activityResult: ActivityResult): PaymentCardUiModel? {
            if (activityResult.resultCode != RESULT_OK) {
                return null
            }
            return activityResult.data?.getParcelableExtraCompat<PaymentCardUiModel>(
                EXTRA_PAYMENT_CARD_UI_MODEL,
            )
        }
    }
}

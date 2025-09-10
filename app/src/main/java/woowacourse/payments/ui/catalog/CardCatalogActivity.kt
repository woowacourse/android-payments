package woowacourse.payments.ui.catalog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.catalog.screen.CardCatalogScreen
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardCatalogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardCatalogScreen()
            }
        }
    }

    companion object {
        const val PAYMENT_CARD_UI_MODEL_KEY =
            "woowacourse.payments.ui.catalog.PAYMENT_CARD_KEY"

        fun newIntent(
            context: Context,
            paymentCardUiModel: PaymentCardUiModel,
        ): Intent =
            Intent(context, CardCatalogActivity::class.java)
                .putExtra(PAYMENT_CARD_UI_MODEL_KEY, paymentCardUiModel)
    }
}
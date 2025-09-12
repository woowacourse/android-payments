package woowacourse.payments.ui.payments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.catalog.CardCatalogActivity
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.payments.screen.CardRegistrationScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardRegistrationScreen(
                    onBackPressed = { finish() },
                    onCardRegistered = { paymentCardUiModel: PaymentCardUiModel ->
                        val intent = CardCatalogActivity.newIntent(this, paymentCardUiModel)
                        setResult(RESULT_OK, intent)
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, CardRegistrationActivity::class.java)
    }
}

package woowacourse.payments.ui.payments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.content.IntentCompat.getSerializableExtra
import woowacourse.payments.ui.catalog.CardCatalogActivity
import woowacourse.payments.ui.catalog.CardCatalogActivity.Companion.PAYMENT_CARD_UI_MODEL_KEY
import woowacourse.payments.ui.common.getSerializableExtraCompat
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
                    registrationState = intent.getSerializableExtraCompat<RegistrationState>(REGISTRATION_STATE_KEY) ?: return@AndroidpaymentsTheme,
                )
            }
        }
    }

    companion object {
        private const val REGISTRATION_STATE_KEY =
            "woowacourse.payments.ui.payments.REGISTRATION_STATE_KEY"

        fun newIntent(
            context: Context,
            registrationState: RegistrationState,
        ): Intent =
            Intent(context, CardRegistrationActivity::class.java)
                .putExtra(REGISTRATION_STATE_KEY, registrationState)
    }
}

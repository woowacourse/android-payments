package woowacourse.payments.ui.payments.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.payments.CardsActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardRegistrationScreen(
                    onBackPressed = { finish() },
                    onCardRegistered = { registeredCard: CardUiModel ->
                        val intent = CardsActivity.newIntent(this, registeredCard)
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

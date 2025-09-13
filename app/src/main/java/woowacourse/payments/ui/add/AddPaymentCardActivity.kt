package woowacourse.payments.ui.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddPaymentCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                AddPaymentCardScreen(
                    onBack = { finish() },
                    onSave = { paymentCard ->
                        setResult(RESULT_OK, Intent().putExtra(EXTRA_CARD, paymentCard))
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        private const val EXTRA_CARD = "extra_card"

        fun newIntent(context: Context): Intent = Intent(context, AddPaymentCardActivity::class.java)
    }
}

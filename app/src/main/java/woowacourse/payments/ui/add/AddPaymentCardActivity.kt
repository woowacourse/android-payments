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

        val editingCardId = intent.getStringExtra(EXTRA_CARD_ID)

        setContent {
            AndroidpaymentsTheme {
                AddPaymentCardScreen(
                    onBack = { finish() },
                    onSave = {
                        setResult(RESULT_OK)
                        finish()
                    },
                    cardId = editingCardId,
                )
            }
        }
    }

    companion object {
        const val EXTRA_CARD_ID = "extra_card_id"

        fun newIntent(context: Context): Intent = Intent(context, AddPaymentCardActivity::class.java)
    }
}

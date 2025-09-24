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
        val isEditingFromIntent = editingCardId != null

        setContent {
            AndroidpaymentsTheme {
                AddPaymentCardScreen(
                    onBack = { finish() },
                    onSave = {
                        val resultIntent =
                            Intent().apply {
                                putExtra(
                                    EXTRA_RESULT_MODE,
                                    if (isEditingFromIntent) RESULT_MODE_EDIT else RESULT_MODE_ADD,
                                )
                            }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    },
                    cardId = editingCardId,
                )
            }
        }
    }

    companion object {
        const val EXTRA_CARD_ID = "extra_card_id"
        const val EXTRA_RESULT_MODE = "extra_result_mode"
        const val RESULT_MODE_ADD = "add"
        const val RESULT_MODE_EDIT = "edit"

        fun newIntent(context: Context): Intent = Intent(context, AddPaymentCardActivity::class.java)
    }
}

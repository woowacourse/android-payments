package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.cards.CardsActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    onBackClick = { onBackPressedDispatcher.onBackPressed() },
                    onSaveClick = { paymentCard ->
                        val intent = CardsActivity.intent(paymentCard)
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                )
            }
        }
    }

    companion object {
        fun instance(context: Context) = Intent(context, NewCardActivity::class.java)
    }
}

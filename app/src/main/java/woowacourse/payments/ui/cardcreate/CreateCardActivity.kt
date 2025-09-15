package woowacourse.payments.ui.cardcreate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.cards.CardsActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CreateCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CreateCardScreen(
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
        fun instance(context: Context) = Intent(context, CreateCardActivity::class.java)
    }
}

package woowacourse.payments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.PaymentScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                PaymentScreenWithLauncher()
            }
        }
    }
}

@Composable
fun PaymentScreenWithLauncher() {
    var cards by remember { mutableStateOf(emptyList<CardUiModel>()) }
    val context = LocalContext.current

    val cardAddLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val newCard = activityResult.data?.getParcelableExtra<CardUiModel>("card")
                if (newCard != null) {
                    cards = cards + newCard
                }
            }
        }

    PaymentScreen(
        cards = cards,
        onAddCardClick = {
            val intent = Intent(context, AddCardActivity::class.java)
            cardAddLauncher.launch(intent)
        },
    )
}

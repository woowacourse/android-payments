package woowacourse.payments.ui.cardList

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.ui.cardRegister.CardRegisterActivity
import woowacourse.payments.ui.common.model.Card
import woowacourse.payments.ui.common.parcelable
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                var cards by remember { mutableStateOf<List<Card>>(emptyList()) }
                val context = LocalContext.current
                val cardAddLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                        if (activityResult.resultCode == RESULT_OK) {
                            val newCard: Card =
                                activityResult.data?.parcelable("newCard")
                                    ?: return@rememberLauncherForActivityResult
                            cards += newCard
                            Toast.makeText(context, "카드가 추가되었습니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                CardListScreen(
                    cards = cards,
                    onRegistrationClick = {
                        cardAddLauncher.launch(CardRegisterActivity.newIntent(context))
                    },
                )
            }
        }
    }
}

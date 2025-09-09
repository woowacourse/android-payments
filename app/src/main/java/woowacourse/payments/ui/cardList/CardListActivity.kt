package woowacourse.payments.ui.cardList

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.ui.cardRegister.CardRegisterActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val context = LocalContext.current
                CardListScreen(
                    onRegistrationClick = {
                        context.startActivity(CardRegisterActivity.newIntent(context))
                    },
                )
            }
        }
    }
}

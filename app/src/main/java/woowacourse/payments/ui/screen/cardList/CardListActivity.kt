package woowacourse.payments.ui.screen.cardList

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardListScreen(
                    cards = listOf("aa", "bb", "cc", "dd", "ee"),
                    navigateToAddCard = {},
                )
            }
        }
    }
}

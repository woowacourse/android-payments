package woowacourse.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.features.cardlist.CardListScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardlistActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardListScreen(
                    onAddCard = { /* Todo: 카드 추가 로직 */ },
                )
            }
        }
    }
}

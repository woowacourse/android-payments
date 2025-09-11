package woowacourse.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.cards.CardsScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.util.showShortToast

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardsScreen(onCardAdded = {
                    showShortToast(getString(R.string.message_card_added))
                })
            }
        }
    }
}

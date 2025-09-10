package woowacourse.payments.ui.cards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardsScreen(MINIMUM_CARD_COUNT_FOR_ADD_BUTTON)
            }
        }
    }

    companion object {
        private const val MINIMUM_CARD_COUNT_FOR_ADD_BUTTON = 1
    }
}

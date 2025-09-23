package woowacourse.payments.cardaddition

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import woowacourse.payments.Card
import woowacourse.payments.EXTRA_CARD
import woowacourse.payments.cardaddition.component.CardAdditionScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardAdditionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardAdditionScreen(
                    onBackClick = ::finish,
                    onCheckClick = ::saveCard,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

    private fun saveCard(card: Card?) {
        if (card == null) return

        setResult(RESULT_OK, Intent().putExtra(EXTRA_CARD, card))
        finish()
    }
}

package woowacourse.payments.view.cardaddition

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import woowacourse.payments.view.CardUiModel
import woowacourse.payments.view.EXTRA_CARD
import woowacourse.payments.view.cardaddition.component.CardAdditionScreen
import woowacourse.payments.view.ui.theme.AndroidpaymentsTheme

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

    private fun saveCard(card: CardUiModel) {
        setResult(RESULT_OK, Intent().putExtra(EXTRA_CARD, card))

        finish()
    }
}

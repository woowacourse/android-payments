package woowacourse.payments.ui.screen.cardAddition

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.cardAddition.component.CardAdditionScreen
import woowacourse.payments.ui.screen.cards.CardsActivity

class CardAdditionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardAdditionScreen(
                onBackClick = { finish() },
                onSaveClick = { card -> navigateToCards(card) }
            )
        }
    }

    private fun navigateToCards(card: CardUiModel) {
        val intent = CardsActivity.newIntent(this, card)
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, CardAdditionActivity::class.java)
    }
}

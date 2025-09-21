package woowacourse.payments.ui.view.new

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.serialization.toSerializationCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.view.cards.CardsActivity

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    onBackClick = { finish() },
                    onSaveClick = { card -> moveToCards(card) },
                    onFinishRequest = { finish() },
                )
            }
        }
    }

    private fun moveToCards(card: Card) {
        val intent =
            CardsActivity.newIntent(this, card.toSerializationCard())
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, NewCardActivity::class.java)
    }
}

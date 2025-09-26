package woowacourse.payments.ui.cardform

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.cardform.state.CardAction
import woowacourse.payments.ui.cards.CardsActivity
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cardAction =
                    intent.getParcelableExtraCompat<CardAction>(EXTRA_ACTION) ?: CardAction.Register
                CardFormScreen(
                    cardAction = cardAction,
                    onBackPressed = { finish() },
                    onSaveClick = { saveCard: CardUiModel ->
                        val intent = CardsActivity.newIntent(this, saveCard, cardAction)
                        setResult(RESULT_OK, intent)
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        private const val EXTRA_ACTION = "EXTRA_ACTION"

        fun newIntent(
            context: Context,
            action: CardAction,
        ): Intent =
            Intent(context, CardFormActivity::class.java).apply {
                putExtra(EXTRA_ACTION, action)
            }
    }
}

package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.cards.CardAction
import woowacourse.payments.ui.cards.CardsActivity
import woowacourse.payments.ui.newcard.create.CreateCardScreen
import woowacourse.payments.ui.newcard.create.CreateCardStateHolder
import woowacourse.payments.ui.newcard.model.NewCardMode
import woowacourse.payments.ui.newcard.update.UpdateCardScreen
import woowacourse.payments.ui.newcard.update.UpdateCardStateHolder
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cardId = intent.getLongExtra(UPDATE_CARD_KEY, -1)
                val mode = NewCardMode.of(cardId)

                when (mode) {
                    NewCardMode.Create -> {
                        CreateCardScreen(
                            CreateCardStateHolder(),
                            {
                                val intent = CardsActivity.intent(this, CardAction.Add(it))
                                setResult(RESULT_OK, intent)
                                finish()
                            },
                            { onBackPressedDispatcher.onBackPressed() },
                            Modifier
                        )
                    }

                    is NewCardMode.Update -> {
                        UpdateCardScreen(
                            UpdateCardStateHolder(),
                            mode.cardId,
                            {
                                val intent = CardsActivity.intent(this, CardAction.Update(cardId))
                                setResult(RESULT_OK, intent)
                                finish()
                            },
                            { onBackPressedDispatcher.onBackPressed() },
                            Modifier
                        )
                    }
                }
            }
        }
    }

    companion object {
        const val UPDATE_CARD_KEY = "update_card_key"
        fun instance(context: Context, cardId: Long? = null) =
            Intent(context, NewCardActivity::class.java).putExtra(UPDATE_CARD_KEY, cardId)
    }
}

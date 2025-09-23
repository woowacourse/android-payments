package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.data.storage.CardStorage
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.common.model.toData
import woowacourse.payments.ui.newcard.model.ActionType

class CardFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionType = intent.getSerializableExtra(EXTRA_ACTION_TYPE) as ActionType
        val initialCard = intent.getParcelableExtra<CardUiModel>(EXTRA_INITIAL_CARD)

        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardFormScreen(
                    actionType = actionType,
                    initialCard = initialCard,
                    saveCard = { card -> saveCard(card) },
                    navigateToBack = { finish() },
                )
            }
        }
    }

    private fun saveCard(card: CardUiModel) {
        CardStorage
            .saveCard(card.toData())
            .onSuccess {
                setResult(RESULT_OK, Intent().putExtra(EXTRA_CARD_RESULT, card))
                finish()
            }
    }

    companion object {
        const val EXTRA_ACTION_TYPE = "EXTRA_ACTION_TYPE"
        const val EXTRA_INITIAL_CARD = "EXTRA_INITIAL_CARD"
        const val EXTRA_CARD_RESULT = "EXTRA_CARD_RESULT"

        fun newIntent(context: Context): Intent =
            Intent(context, CardFormActivity::class.java).apply {
                putExtra(EXTRA_ACTION_TYPE, ActionType.NEW)
            }

        fun newIntent(
            context: Context,
            card: CardUiModel,
        ): Intent =
            Intent(context, CardFormActivity::class.java).apply {
                putExtra(EXTRA_ACTION_TYPE, ActionType.EDIT)
                putExtra(EXTRA_INITIAL_CARD, card)
            }
    }
}

package woowacourse.payments.ui.view.new

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.core.ext.getParcelableCompat
import woowacourse.payments.ui.serialization.toSerializationCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.view.cards.CardsActivity
import woowacourse.payments.ui.view.cards.CardsActivity.Companion.EXTRA_CARD_MODIFY_INDEX

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                intent.getParcelableCompat<NewCardMode>(EXTRA_MODE)?.let { mode ->
                    NewCardScreen(
                        mode = mode,
                        onBackClick = { finish() },
                        onSaveClick = { card ->
                            moveToCards(card, mode)
                        },
                        onFinishRequest = { finish() },
                    )
                }
            }
        }
    }

    private fun moveToCards(
        card: Card,
        mode: NewCardMode,
    ) {
        val cardExtra =
            when (mode) {
                NewCardMode.Add -> CardsActivity.EXTRA_CARD_ADD
                is NewCardMode.Modify -> CardsActivity.EXTRA_CARD_MODIFY
            }

        val resultIntent =
            Intent().apply {
                putExtra(cardExtra, card.toSerializationCard())
                if (mode is NewCardMode.Modify) putExtra(EXTRA_CARD_MODIFY_INDEX, mode.index)
            }
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    companion object {
        private const val EXTRA_MODE = "newCardMode"

        fun newIntent(
            context: Context,
            mode: NewCardMode,
        ): Intent =
            Intent(context, NewCardActivity::class.java).apply {
                putExtra(EXTRA_MODE, mode)
            }
    }
}

package woowacourse.payments.ui.view.cards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.serialization.SerializationCard
import woowacourse.payments.ui.serialization.toSerializationCard
import woowacourse.payments.ui.state.CardState
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.view.new.NewCardActivity
import woowacourse.payments.ui.view.new.NewCardMode

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardsScreen(
                    onClickToolbarAddAction = { launcher ->
                        launcher.launch(NewCardActivity.newIntent(this, NewCardMode.Add))
                    },
                    onClickCard = { launcher, cardType ->
                        val mode =
                            when (cardType) {
                                CardState.Empty -> NewCardMode.Add
                                is CardState.Registered -> NewCardMode.Modify(cardType.card.toSerializationCard())
                                CardState.Pending -> return@CardsScreen
                            }
                        launcher.launch(NewCardActivity.newIntent(this, mode))
                    },
                )
            }
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            card: SerializationCard,
        ): Intent =
            Intent(context, CardsActivity::class.java)
                .apply { putExtra(EXTRA_CARD, card) }

        const val EXTRA_CARD = "extra_card"
    }
}

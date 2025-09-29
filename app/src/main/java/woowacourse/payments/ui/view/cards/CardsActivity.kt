package woowacourse.payments.ui.view.cards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
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
                    onClickAddCard = { launcher ->
                        moveToAddCard(launcher)
                    },
                    onClickModifyCard = { launcher, cardType, index ->
                        moveToModifyCard(launcher, cardType, index)
                    },
                )
            }
        }
    }

    private fun moveToAddCard(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        launcher.launch(NewCardActivity.newIntent(this, NewCardMode.Add))
    }

    private fun moveToModifyCard(
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
        cardType: CardState,
        index: Int,
    ) {
        val mode =
            when (cardType) {
                CardState.Empty -> NewCardMode.Add
                is CardState.Registered ->
                    NewCardMode.Modify(cardType.card.toSerializationCard(), index)

                CardState.Pending -> return
            }
        launcher.launch(NewCardActivity.newIntent(this, mode))
    }

    companion object {
        fun newIntent(
            context: Context,
            card: SerializationCard,
        ): Intent =
            Intent(context, CardsActivity::class.java)
                .apply { putExtra(EXTRA_CARD_ADD, card) }

        const val EXTRA_CARD_ADD = "extra_card_add"
        const val EXTRA_CARD_MODIFY = "extra_card_MODIFY"
        const val EXTRA_CARD_MODIFY_INDEX = "extra_card_modify_index"
    }
}

package woowacourse.payments.ui.cardlist

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.newcard.model.CardUpdateType
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.getParcelableCompat

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cards: MutableList<CardUiModel> = rememberSaveable { mutableStateListOf() }
                var cardUpdateType: CardUpdateType = CardUpdateType.Add

                val newCardLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                        if (result.resultCode == Activity.RESULT_OK) {
                            result.data
                                ?.getParcelableCompat<CardUiModel>(NewCardActivity.INTENT_CARD_KEY)
                                ?.let { newCard: CardUiModel ->
                                    when (val type = cardUpdateType) {
                                        CardUpdateType.Add -> cards.add(newCard)
                                        is CardUpdateType.Edit -> {
                                            val index = cards.indexOf(type.card)
                                            if (index != -1) {
                                                cards[index] = newCard
                                            }
                                        }
                                    }
                                }
                        }
                    }

                CardListScreen(
                    cards = cards,
                    onAddCardClick = {
                        newCardLauncher.launch(NewCardActivity.newIntent(this, null))
                        cardUpdateType = CardUpdateType.Add
                    },
                    onCardClick = { card: CardUiModel ->
                        newCardLauncher.launch(NewCardActivity.newIntent(this, card))
                        cardUpdateType = CardUpdateType.Edit(card)
                    },
                )
            }
        }
    }
}

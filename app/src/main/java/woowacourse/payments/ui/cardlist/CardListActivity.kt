package woowacourse.payments.ui.cardlist

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.cardupdate.CardUpdateActivity
import woowacourse.payments.ui.cardupdate.model.CardUpdateType
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.getParcelableCompat

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cards: MutableList<CardUiModel> = rememberSaveable { mutableStateListOf() }
                var cardUpdateType by rememberSaveable {
                    mutableStateOf<CardUpdateType>(CardUpdateType.Add)
                }

                val cardUpdateLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                        if (result.resultCode == Activity.RESULT_OK) {
                            result.data
                                ?.getParcelableCompat<CardUiModel>(CardUpdateActivity.INTENT_CARD_KEY)
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
                        cardUpdateLauncher.launch(CardUpdateActivity.newIntent(this, null))
                        cardUpdateType = CardUpdateType.Add
                    },
                    onCardClick = { card: CardUiModel ->
                        cardUpdateLauncher.launch(CardUpdateActivity.newIntent(this, card))
                        cardUpdateType = CardUpdateType.Edit(card)
                    },
                )
            }
        }
    }
}

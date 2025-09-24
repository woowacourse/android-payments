package woowacourse.payments.ui.cardlist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.getParcelableCompat

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cards: MutableList<CardUiModel> = rememberSaveable { mutableStateListOf() }

                val newCardLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                        if (result.resultCode == NewCardActivity.RESULT_CODE_SAVE) {
                            result.data
                                ?.getParcelableCompat<CardUiModel>(NewCardActivity.INTENT_CARD_KEY)
                                ?.let { newCard: CardUiModel ->
                                    cards.add(newCard)
                                    Log.d("jiyuneel", "카드 추가")
                                }
                        } else if (result.resultCode == NewCardActivity.RESULT_CODE_EDIT) {
                            result.data
                                ?.getParcelableCompat<CardUiModel>(NewCardActivity.INTENT_CARD_KEY)
                                ?.let { newCard: CardUiModel ->
                                    Log.d("jiyuneel", "카드 수정")
                                }
                        }
                    }

                CardListScreen(
                    cards = cards,
                    onAddCardClick = {
                        newCardLauncher.launch(NewCardActivity.newIntent(this, null))
                    },
                    onCardClick = { card: CardUiModel ->
                        newCardLauncher.launch(NewCardActivity.newIntent(this, card))
                    },
                )
            }
        }
    }
}

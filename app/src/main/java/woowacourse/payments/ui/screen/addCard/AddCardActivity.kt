package woowacourse.payments.ui.screen.addCard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.cardList.CardListActivity.Companion.EDIT_CARD_KEY
import woowacourse.payments.ui.screen.cardList.CardListActivity.Companion.NEW_CARD_KEY
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.getParcelableExtraCompat

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val cardToEdit: CardUiModel? = intent.getParcelableExtraCompat(EDIT_CARD_KEY)
        setContent {
            AndroidpaymentsTheme {
                val stateHolder =
                    rememberSaveable(saver = AddCardStateHolder.saver) {
                        AddCardStateHolder(cardToEdit?.toAddCardUiState() ?: AddCardUiState())
                    }

                AddCardScreen(
                    stateHolder = stateHolder,
                    onBackPressed = { finish() },
                    onCardSaved = { cardUiModel ->
                        val cardWithId =
                            cardToEdit?.let { cardUiModel.copy(id = it.id) } ?: cardUiModel
                        val resultIntent = Intent().apply { putExtra(NEW_CARD_KEY, cardWithId) }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    },
                )
            }
        }
    }
}

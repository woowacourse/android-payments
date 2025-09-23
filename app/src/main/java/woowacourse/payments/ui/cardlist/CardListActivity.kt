package woowacourse.payments.ui.cardlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.model.toDomain
import woowacourse.payments.ui.newcard.state.NewCardStatus

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardCatalogScreen(
                onEditCard = ::onEditCard,
            )
        }
    }

    fun onEditCard(cardUiModel: CardUiModel) {
        val intent = NewCardActivity.Intent(NewCardStatus.EditCard(cardUiModel), this)
        startActivity(intent)
    }

    companion object {
        private const val EXTRA_NEW_CARD = "newCard"
        fun Intent(context: Context, newCard: CardUiModel): Intent {
            val intent = Intent(context, CardListActivity::class.java)
            intent.putExtra(EXTRA_NEW_CARD, newCard)
            return intent
        }
    }
}
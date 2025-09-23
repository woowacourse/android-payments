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
import woowacourse.payments.ui.model.NewCardMode
import woowacourse.payments.ui.model.toDomain

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardCatalogScreen(
                onAddCard = ::onAddCard,
                onEditCard = ::onEditCard,
            )
        }
    }

    fun onAddCard() {
        val intent = NewCardActivity.Intent(NewCardMode.CreateMode, this)
        startActivity(intent)
    }

    fun onEditCard(cardUiModel: CardUiModel) {
        val intent = NewCardActivity.Intent(NewCardMode.EditMode, this, cardUiModel)
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
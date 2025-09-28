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
            CardCatalogScreen()
        }
    }

    companion object {
        private const val EXTRA_NEW_CARD = "newCard"
        private const val EXTRA_OLD_CARD = "oldCard"
        fun createCardIntent(context: Context, newCard: CardUiModel): Intent {
            val intent = Intent(context, CardListActivity::class.java)
            intent.putExtra(EXTRA_NEW_CARD, newCard)
            return intent
        }

        fun editCardIntent(context: Context, newCard: CardUiModel, oldCard: CardUiModel? = null): Intent {
            val intent = Intent(context, CardListActivity::class.java)
            intent.putExtra(EXTRA_NEW_CARD, newCard)
            intent.putExtra(EXTRA_OLD_CARD, oldCard)
            return intent
        }
    }
}
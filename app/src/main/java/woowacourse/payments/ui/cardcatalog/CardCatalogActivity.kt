package woowacourse.payments.ui.cardcatalog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.newcard.uiModel.NewCardMode

class CardCatalogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardCatalogScreen({ card ->
                val intent = NewCardActivity.Intent(NewCardMode.EditMode, this, card)
                startActivity(intent)
            })
        }
    }

    companion object {
        private const val EXTRA_NEW_CARD = "newCard"
        fun Intent(context: Context, newCard: Card): Intent {
            val intent = Intent(context, CardCatalogActivity::class.java)
            intent.putExtra(EXTRA_NEW_CARD, newCard)
            return intent
        }
    }
}
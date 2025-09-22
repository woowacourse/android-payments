package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cardcatalog.CardCatalogActivity.Companion.Intent
import woowacourse.payments.ui.core.getParcelableCompat
import woowacourse.payments.ui.newcard.component.NewCardScreen
import woowacourse.payments.ui.newcard.uiModel.NewCardMode

class NewCardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val card = intent.getParcelableCompat<Card>(KEY_INTENT_CARD)
        setContent {
            NewCardScreen(
                navigateToBack = { navigateToBack() },
                onSaveClick = { card -> saveClick(card) },
                card = card
            )
        }
    }

    fun navigateToBack() {
        finish()
    }

    fun saveClick(newCard: Card?) {
        newCard?.let {
            val intent = Intent(context = this, newCard)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        private const val KEY_INTENT_CARD = "card"
        fun Intent(newCardMode: NewCardMode, context: Context, card: Card?): Intent {
            when (newCardMode) {
                is NewCardMode.CreateMode -> {
                    val intent = Intent(context, NewCardActivity::class.java)
                    return intent
                }

                is NewCardMode.EditMode -> {
                    val intent = Intent(context, NewCardActivity::class.java)
                    intent.putExtra(KEY_INTENT_CARD, card)
                    return intent
                }
            }
        }
    }
}
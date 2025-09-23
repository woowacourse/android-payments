package woowacourse.payments.ui.newcard

import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.cardlist.CardListActivity.Companion.newIntent
import woowacourse.payments.ui.core.getParcelableCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.state.NewCardStatus

class NewCardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val cardUiModel: CardUiModel? = intent.getParcelableCompat<CardUiModel>(KEY_INTENT_CARD)
        val newCardStatus: NewCardStatus =
            intent.getParcelableCompat<NewCardStatus>(KEY_INTENT_NEW_CARD_MODE)
                ?: NewCardStatus.CreateCard

        setContent {
            NewCardScreen(
                newCardStatus = newCardStatus,
                cardUiModel = cardUiModel,
                navigateToBack = ::finish,
                onClickSaveCard = ::onSaveCard,
                onClickUpdateCard = { oldCard, newCard -> onUpdateCard(oldCard, newCard) },
                showToastMessage = ::showToastMessage
            )
        }
    }

    fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun onSaveCard(newCard: CardUiModel?) {
        newCard?.let {
            val intent = newIntent(context = this, newCard)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    fun onUpdateCard(oldCard: CardUiModel?, newCard: CardUiModel?) {
        newCard?.let {
            val intent = newIntent(context = this, newCard, oldCard)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        private const val KEY_INTENT_CARD = "card"
        private const val KEY_INTENT_NEW_CARD_MODE = "new_card_mode"
        fun Intent(context: Context, newCardStatus: NewCardStatus): Intent {
            when (newCardStatus) {
                is NewCardStatus.CreateCard -> {
                    val intent = Intent(context, NewCardActivity::class.java)
                    intent.putExtra(KEY_INTENT_NEW_CARD_MODE, newCardStatus)
                    return intent
                }

                is NewCardStatus.EditCard -> {
                    val intent = Intent(context, NewCardActivity::class.java)
                    intent.apply {
                        putExtra(KEY_INTENT_CARD, newCardStatus.cardUiModel)
                        putExtra(KEY_INTENT_NEW_CARD_MODE, newCardStatus)
                    }

                    return intent
                }
            }
        }
    }
}
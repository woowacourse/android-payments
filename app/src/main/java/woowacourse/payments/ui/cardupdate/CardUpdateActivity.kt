package woowacourse.payments.ui.cardupdate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.cardupdate.model.CardUpdateType
import woowacourse.payments.ui.cardupdate.model.toUiModel
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.getParcelableCompat

class CardUpdateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val card: CardUiModel? = intent.getParcelableCompat(INTENT_CARD_KEY)
        val updateType: CardUpdateType = card?.let(CardUpdateType::Edit) ?: CardUpdateType.Add
        setContent {
            AndroidpaymentsTheme {
                CardUpdateScreen(
                    updateType = updateType,
                    companies = CardCompany.entries.map(CardCompany::toUiModel),
                    onBackClick = { finish() },
                    onSaveClick = { newCard: CardUiModel ->
                        saveCard(newCard)
                        val message: String =
                            when (updateType) {
                                CardUpdateType.Add -> getString(R.string.card_added_message)
                                is CardUpdateType.Edit -> getString(R.string.card_edited_message)
                            }
                        showToast(message)
                        finish()
                    },
                )
            }
        }
    }

    private fun saveCard(card: CardUiModel) {
        val intent = Intent().apply { putExtra(INTENT_CARD_KEY, card) }
        setResult(RESULT_OK, intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val INTENT_CARD_KEY = "card"

        fun newIntent(
            context: Context,
            card: CardUiModel?,
        ): Intent =
            Intent(context, CardUpdateActivity::class.java).apply {
                putExtra(INTENT_CARD_KEY, card)
            }
    }
}

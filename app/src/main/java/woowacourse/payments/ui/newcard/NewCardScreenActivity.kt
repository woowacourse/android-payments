package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class NewCardScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val existingCard = intent.getParcelableExtra<CardUiModel>(EXISTING_CARD)
        val existingCardIndex = intent.getIntExtra(EXISTING_CARD_INDEX, -1).takeIf { it != -1 }

        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    existingCard = existingCard,
                    onBackClick = { finish() },
                    onSaveClick = { paymentCard ->
                        val resultIntent =
                            Intent().apply {
                                putExtra(ADD_NEW_CARD, paymentCard)
                                existingCardIndex?.let { putExtra(EXISTING_CARD_INDEX, it) }
                            }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        const val ADD_NEW_CARD = "ADD_NEW_CARD"
        const val EXISTING_CARD = "EXISTING_CARD"
        const val EXISTING_CARD_INDEX = "EXISTING_CARD_INDEX"

        fun newIntent(
            context: Context,
            existingCard: CardUiModel? = null,
            index: Int? = null,
        ): Intent =
            Intent(context, NewCardScreenActivity::class.java).apply {
                existingCard?.let { putExtra(EXISTING_CARD, it) }
                index?.let { putExtra(EXISTING_CARD_INDEX, it) }
            }
    }
}

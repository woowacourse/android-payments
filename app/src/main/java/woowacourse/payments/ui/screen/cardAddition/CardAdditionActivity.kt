package woowacourse.payments.ui.screen.cardAddition

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.common.extension.getParcelableCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.cardAddition.component.CardAdditionScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardAdditionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val card = intent.getParcelableCompat<CardUiModel>(EXTRA_CARD)
        setContent {
            AndroidpaymentsTheme {
                CardAdditionScreen(
                    card = card,
                    onBackClick = { finish() },
                    onSaveClick = { result -> navigateToCards(result) },
                )
            }
        }
    }

    private fun navigateToCards(card: CardUiModel) {
        val intent =
            Intent().apply {
                putExtra(EXTRA_CARD, card)
            }
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val EXTRA_CARD = "EXTRA_CARD"

        fun newIntent(context: Context): Intent = Intent(context, CardAdditionActivity::class.java)

        fun newIntentForEdit(
            context: Context,
            card: CardUiModel,
        ): Intent =
            Intent(context, CardAdditionActivity::class.java).apply {
                putExtra(EXTRA_CARD, card)
            }
    }
}

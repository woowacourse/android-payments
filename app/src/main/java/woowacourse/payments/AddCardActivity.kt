package woowacourse.payments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.screen.AddCardScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                AddCardScreen(
                    onBackPressed = {
                        finish()
                    },
                    onAddCard = { card ->
                        val resultIntent =
                            Intent().apply {
                                putExtra(EXTRA_RESULT_CARD, card.toUiModel())
                            }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        private const val EXTRA_RESULT_CARD = "RESULT_CARD"

        fun newIntent(context: Context): Intent = Intent(context, AddCardActivity::class.java)

        fun parseResult(data: Intent?): CardUiModel? = data?.let { IntentCompat.getParcelableExtra<CardUiModel>(it, EXTRA_RESULT_CARD) }
    }
}

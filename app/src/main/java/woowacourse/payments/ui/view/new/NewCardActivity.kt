package woowacourse.payments.ui.view.new

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.core.ext.getParcelableCompat
import woowacourse.payments.ui.serialization.toSerializationCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.view.cards.CardsActivity

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    onBackClick = { finish() },
                    onSaveClick = { card -> moveToCards(card) },
                    onFinishRequest = { finish() },
                )
            }
        }

        intent.getParcelableCompat<NewCardMode>(EXTRA_MODE)?.let {
            when (it) {
                NewCardMode.Add -> Log.d("NewCardActivity123", "Add")
                is NewCardMode.Modify -> Log.d("NewCardActivity123", "Modify")
            }
        }
    }

    private fun moveToCards(card: Card) {
        val intent =
            CardsActivity.newIntent(this, card.toSerializationCard())
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        private const val EXTRA_MODE = "newCardMode"

        fun newIntent(
            context: Context,
            mode: NewCardMode,
        ): Intent =
            Intent(context, NewCardActivity::class.java).apply {
                putExtra(EXTRA_MODE, mode)
            }
    }
}

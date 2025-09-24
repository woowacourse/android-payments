package woowacourse.payments.cardediting

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import woowacourse.payments.CardUiModel
import woowacourse.payments.EXTRA_CARD
import woowacourse.payments.EXTRA_NEW_CARD
import woowacourse.payments.EXTRA_OLD_CARD
import woowacourse.payments.cardediting.component.CardEditingScreen
import woowacourse.payments.getParcelableExtraCompat
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardEditingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val card: CardUiModel = intent.getParcelableExtraCompat(EXTRA_CARD) ?: return finish()

        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val stateHolder: CardEditingStateHolder =
                    rememberCardEditingStateHolder(CardEditingUiState(card))

                CardEditingScreen(
                    stateHolder = stateHolder,
                    onBackClick = ::finish,
                    onCheckClick = ::editCard,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

    private fun editCard(
        old: CardUiModel,
        new: CardUiModel,
    ) {
        val result =
            Intent()
                .putExtra(EXTRA_OLD_CARD, old)
                .putExtra(EXTRA_NEW_CARD, new)

        setResult(RESULT_OK, result)

        finish()
    }
}

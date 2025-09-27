package woowacourse.payments.view.cardediting

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import woowacourse.payments.R
import woowacourse.payments.view.EXTRA_CARD
import woowacourse.payments.view.EXTRA_NEW_CARD
import woowacourse.payments.view.EXTRA_OLD_CARD
import woowacourse.payments.view.cardediting.component.CardEditingScreen
import woowacourse.payments.view.getParcelableExtraCompat
import woowacourse.payments.view.ui.model.CardUiModel
import woowacourse.payments.view.ui.theme.AndroidpaymentsTheme

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
                    onCardSaveSuccess = {
                        Toast
                            .makeText(
                                this,
                                getString(R.string.card_editing_edit_card_success_message),
                                Toast.LENGTH_SHORT,
                            ).show()

                        setResult(RESULT_OK)
                        finish()
                    },
                    onCardSaveFailure = {
                        Toast
                            .makeText(
                                this,
                                getString(R.string.card_addition_add_card_failure_message),
                                Toast.LENGTH_SHORT,
                            ).show()
                    },
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

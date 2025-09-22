package woowacourse.payments.ui.screen.addCard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.screen.cardList.CardListActivity.Companion.NEW_CARD_KEY
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val stateHolder =
                    rememberSaveable(saver = AddCardStateHolder.saver) { AddCardStateHolder() }

                AddCardScreen(
                    onBackPressed = { finish() },
                    onCardSaved = { cardUiModel ->
                        val resultIntent =
                            Intent().apply {
                                putExtra(NEW_CARD_KEY, cardUiModel)
                            }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    },
                    stateHolder = stateHolder,
                )
            }
        }
    }
}

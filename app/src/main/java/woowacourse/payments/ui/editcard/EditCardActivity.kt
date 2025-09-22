package woowacourse.payments.ui.editcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.getParcelableExtraCompat
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class EditCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val registeredCard =
            intent.getParcelableExtraCompat<Card>(KEY_CARD_TO_EDIT) ?: run {
                finish()
                return
            }
        setContent {
            AndroidpaymentsTheme {
                EditCardScreen(
                    card = registeredCard,
                    onBackClick = { finish() },
                    onSaveClick = { card ->
                        val intent =
                            Intent().apply {
                                putExtra(KEY_EDITED_CARD, card)
                            }
                        setResult(RESULT_OK, intent)
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        const val KEY_CARD_TO_EDIT = "card_to_edit"
        const val KEY_EDITED_CARD = "edited_card"

        fun newIntent(
            context: Context,
            card: Card,
        ): Intent =
            Intent(context, EditCardActivity::class.java).apply {
                putExtra(KEY_CARD_TO_EDIT, card)
            }
    }
}

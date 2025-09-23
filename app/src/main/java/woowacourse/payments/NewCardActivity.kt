package woowacourse.payments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import woowacourse.payments.cards.CardParcelable
import woowacourse.payments.newcard.NewCardScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.util.parcelable
import woowacourse.payments.util.showShortToast

@OptIn(ExperimentalMaterial3Api::class)
class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen(
                    card = intent.parcelable<CardParcelable>(KEY_CARD),
                    onBackClick = { finish() },
                    onSaveClick = { card, isEditMode ->
                        val resultIntent = Intent().putExtra(KEY_CARD, card)
                        setResult(if (isEditMode) RESULT_EDITED else RESULT_ADDED, resultIntent)
                        finish()
                    },
                    onCardSaveFailed = { showShortToast(getString(R.string.card_save_failed)) },
                )
            }
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            cardParcelable: CardParcelable?,
        ) = Intent(context, NewCardActivity::class.java).apply {
            putExtra(KEY_CARD, cardParcelable)
        }

        const val KEY_CARD = "key_card"
        const val RESULT_ADDED = 200
        const val RESULT_EDITED = 201
    }
}

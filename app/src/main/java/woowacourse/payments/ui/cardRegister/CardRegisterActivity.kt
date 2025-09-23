package woowacourse.payments.ui.cardRegister

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.R
import woowacourse.payments.ui.cardList.CardListActivity
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.common.parcelable
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardRegisterScreen(
                    onBackClick = { finish() },
                    onSaveClick = { card: CardUiModel ->
                        val intent =
                            Intent().putExtra(
                                CardListActivity.NEW_CARD_KEY,
                                card,
                            )
                        setResult(RESULT_OK, intent)
                        finish()
                    },
                    isNotValidInput = {
                        Toast
                            .makeText(
                                this,
                                getString(R.string.card_input_not_valid_message),
                                Toast.LENGTH_SHORT,
                            ).show()
                    },
                    cardRegisterState =
                        rememberCardRegisterState(
                            card = intent.parcelable(EDIT_CARD_KEY),
                        ),
                )
            }
        }
    }

    companion object {
        private const val EDIT_CARD_KEY = "woowacourse.payments.ui.cardRegister"

        fun newIntent(
            context: Context,
            card: CardUiModel? = null,
        ): Intent =
            Intent(context, CardRegisterActivity::class.java).apply {
                putExtra(EDIT_CARD_KEY, card)
            }
    }
}

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
                    editMode = intent.getBooleanExtra("EDIT_MODE", false),
                    onBackClick = { finish() },
                    onSaveClick = { card: CardUiModel ->
                        val intent =
                            Intent().putExtra(
                                CardListActivity.NEW_CARD_KEY,
                                card,
                            )
                        setResult(NEW_CARD_SAVE_RESULT_OK, intent)
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
                    isNotChangedInput = {
                        Toast
                            .makeText(
                                this,
                                getString(R.string.card_input_not_changed_message),
                                Toast.LENGTH_SHORT,
                            ).show()
                    },
                    onEditingSaveClick = { card: CardUiModel ->
                        val intent =
                            Intent().putExtra(
                                CardListActivity.EDITED_CARD_KEY,
                                card,
                            )
                        setResult(EDIT_CARD_SAVE_RESULT_OK, intent)
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        private const val EDIT_CARD_KEY = "woowacourse.payments.ui.cardRegister"
        const val NEW_CARD_SAVE_RESULT_OK = 100
        const val EDIT_CARD_SAVE_RESULT_OK = 101

        fun newIntent(
            context: Context,
            editMode: Boolean = false,
            card: CardUiModel? = null,
        ): Intent =
            Intent(context, CardRegisterActivity::class.java).apply {
                putExtra("EDIT_MODE", editMode)
                putExtra(EDIT_CARD_KEY, card)
            }
    }
}

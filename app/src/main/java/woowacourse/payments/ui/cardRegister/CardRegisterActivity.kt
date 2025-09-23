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
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, CardRegisterActivity::class.java)
    }
}

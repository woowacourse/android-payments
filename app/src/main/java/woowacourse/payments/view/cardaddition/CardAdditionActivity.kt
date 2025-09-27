package woowacourse.payments.view.cardaddition

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import woowacourse.payments.R
import woowacourse.payments.view.cardaddition.component.CardAdditionScreen
import woowacourse.payments.view.ui.model.CardUiModel
import woowacourse.payments.view.ui.theme.AndroidpaymentsTheme

class CardAdditionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardAdditionScreen(
                    onBackClick = ::finish,
                    onCardSaveSuccess = {
                        Toast
                            .makeText(
                                this,
                                getString(R.string.card_addition_add_card_success_message),
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

    private fun saveCard(card: CardUiModel) {
    }
}

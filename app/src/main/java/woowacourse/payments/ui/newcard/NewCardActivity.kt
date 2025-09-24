package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.cards.CardsActivity
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.create.CreateCardScreen
import woowacourse.payments.ui.newcard.model.NewCardMode
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.utils.ext.parcelable

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val paymentCardUiModel = intent.parcelable<PaymentCardUiModel>(UPDATE_CARD_KEY)
                val mode = NewCardMode.of(paymentCardUiModel)
                NewCardScreen(
                    onBackClick = { onBackPressedDispatcher.onBackPressed() },
                    onSaveClick = { paymentCard ->
                        val intent = CardsActivity.intent(this, paymentCard)
                        setResult(RESULT_OK, intent)
                        finish()
                    },
                    mode = mode
                )
            }
        }
    }

    companion object {
        const val UPDATE_CARD_KEY = "update_card_key"
        fun instance(context: Context, cardUiModel: PaymentCardUiModel? = null) =
            Intent(context, NewCardActivity::class.java).putExtra(UPDATE_CARD_KEY, cardUiModel)
    }
}

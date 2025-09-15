package woowacourse.payments.ui.cards

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardsScreen(Modifier.fillMaxWidth())
            }
        }
    }

    companion object {
        const val NEW_CARD_KEY = "new_card_key"

        fun intent(cardUiModel: PaymentCardUiModel) = Intent().putExtra(NEW_CARD_KEY, cardUiModel)
    }
}

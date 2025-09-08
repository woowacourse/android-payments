package woowacourse.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.screen.PaymentScreen

class PaymentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaymentScreen(
                cards = emptyList(),
                onAddNewCardClick = { navigateToCardAddition() }
            )
        }

    }

    fun navigateToCardAddition() {
        val intent = CardAdditionActivity.Intent(this)
        startActivity(intent)
    }
}
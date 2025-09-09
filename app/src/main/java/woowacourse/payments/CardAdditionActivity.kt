package woowacourse.payments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.screen.CardAdditionScreen

class CardAdditionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardAdditionScreen(navigateToBack = { navigateToBack() }, onSaveClick = { onSaveClick() })
        }
    }

    fun navigateToBack() {
        finish()
    }

    fun onSaveClick() {
        val newCard = PaymentCard(
            cardNumber = "1234 5678 9012 3456",
            expirationDate = "12/25",
            cardOwnerName = "홍길동",
            password = "1234"
        )
        intent = Intent().apply {
            putExtra("newCard", newCard)
        }
        setResult(Activity.RESULT_OK,  intent)
        finish()
    }
    companion object {
        fun Intent(context: Context): Intent = Intent(context, CardAdditionActivity::class.java)
    }
}


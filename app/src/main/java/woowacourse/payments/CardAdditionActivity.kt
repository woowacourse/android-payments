package woowacourse.payments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.screen.CardAdditionScreen

class CardAdditionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardAdditionScreen(navigateToBack = { navigateToBack() })
        }
    }

    fun navigateToBack() {
        finish()
    }
    companion object {
        fun Intent(context: Context): Intent = Intent(context, CardAdditionActivity::class.java)
    }
}


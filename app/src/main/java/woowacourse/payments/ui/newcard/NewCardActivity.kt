package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewCardScreen(
                navigateToBack = { navigateToBack() },
                onSaveClick = { card -> onSaveClick(card) })
        }
    }

    fun navigateToBack() {
        finish()
    }

    fun onSaveClick(newCard: Card) {
        intent = android.content.Intent().apply {
            putExtra("newCard", newCard)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        fun Intent(context: Context): Intent = Intent(context, NewCardActivity::class.java)
    }
}
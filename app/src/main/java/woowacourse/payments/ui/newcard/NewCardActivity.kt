package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import woowacourse.payments.ui.cardcatalog.CardCatalogActivity.Companion.Intent
import android.os.Bundle
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
                onSaveClick = { newCard: Card -> onSaveClick(newCard) })
        }
    }

    fun navigateToBack() {
        finish()
    }

    fun onSaveClick(newCard: Card) {
        val intent = Intent(context = this, newCard = newCard)
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        fun Intent(context: Context): Intent = Intent(context, NewCardActivity::class.java)
    }
}
package woowacourse.payments.ui.CardCatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.newcard.NewCardActivity

class CardCatalogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardCatalogScreen(
                onAddNewCardClick = { navigateToCardAddition() }
            )
        }
    }
    fun navigateToCardAddition() {
        val intent = NewCardActivity.Companion.Intent(this)
        startActivity(intent)
    }
}
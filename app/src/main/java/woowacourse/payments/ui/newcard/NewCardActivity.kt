package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import woowacourse.payments.ui.cardcatalog.CardCatalogActivity.Companion.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.CardStateHolder

class NewCardActivity : ComponentActivity() {
    private val state by lazy { CardStateHolder() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankSelectBottomSheet()
            NewCardScreen(
                navigateToBack = { navigateToBack() },
                onSaveClick = { saveClick() },
                state = state
            )
        }
    }

    fun navigateToBack() {
        finish()
    }

    fun saveClick() {
        val intent = Intent(context = this)
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        fun Intent(context: Context): Intent = Intent(context, NewCardActivity::class.java)
    }
}
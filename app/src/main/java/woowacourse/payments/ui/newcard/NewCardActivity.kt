package woowacourse.payments.ui.newcard

import android.content.Context
import android.content.Intent
import woowacourse.payments.ui.cardcatalog.CardCatalogActivity.Companion.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.newcard.CardStateHolder
import woowacourse.payments.ui.newcard.component.SelectedBankBottomSheet

class NewCardActivity : ComponentActivity() {
    private val state by lazy { CardStateHolder() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var bankSheetVisible by rememberSaveable { mutableStateOf(false) }

            SelectedBankBottomSheet(
                isVisible = bankSheetVisible,
                state = state,
                onDismissRequest = { true }
            )
            NewCardScreen(
                navigateToBack = { navigateToBack() },
                onSaveClick = { saveClick() },
                state = state,
                onOpenBankSheet = { bankSheetVisible = true },
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
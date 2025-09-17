package woowacourse.payments.ui.newcard

import woowacourse.payments.ui.cardcatalog.CardCatalogActivity.Companion.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.newcard.component.NewCardScreen
import woowacourse.payments.ui.newcard.state.CardStateHolder
import woowacourse.payments.ui.newcard.component.SelectedBankBottomSheet

class NewCardActivity : ComponentActivity() {
    private val state by lazy { CardStateHolder() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isBankBottomSheetOpen by rememberSaveable { mutableStateOf(true) }
            if (isBankBottomSheetOpen) {
                SelectedBankBottomSheet(
                    state = state,
                    onDismissRequest = { isBankBottomSheetOpen = false }
                )
            }
            NewCardScreen(
                navigateToBack = { navigateToBack() },
                onSaveClick = { saveClick() },
                state = state,
            )
        }
    }

    fun navigateToBack() {
        finish()
    }

    fun saveClick() {
        val newCard = state.newCard()
        if (newCard == null) {
            Toast.makeText(this, state.cardErrorMessage ?: "입력을 확인해 주세요.", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val intent = Intent(context = this, newCard)
        setResult(RESULT_OK, intent)
        finish()
    }
}
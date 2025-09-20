package woowacourse.payments.ui.newcard

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import woowacourse.payments.ui.cardcatalog.CardCatalogActivity.Companion.Intent
import woowacourse.payments.ui.newcard.component.NewCardScreen
import woowacourse.payments.ui.newcard.component.SelectedBankBottomSheet
import woowacourse.payments.ui.newcard.state.CardStateHolder

class NewCardActivity : ComponentActivity() {
    private val state by lazy { CardStateHolder() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
            state.changeBottomSheetState()
            Toast.makeText(this, state.cardErrorMessage ?: "카드사를 선택해주세요", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val intent = Intent(context = this, newCard)
        setResult(RESULT_OK, intent)
        finish()
    }
}
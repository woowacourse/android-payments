package woowacourse.payments.ui.addcard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.model.CardScreenCategory

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var card by remember { mutableStateOf(Card()) }
            var isSheetVisible by remember { mutableStateOf(true) }
            CardScreen(
                card = card,
                cardScreenCategory = CardScreenCategory.Add,
                onBackClick = { finish() },
                onSaveClick = {
                    val resultIntent = Intent()
                    resultIntent.putExtra(EXTRA_CARD, card.toUiModel())
                    setResult(RESULT_OK, resultIntent)
                    finish()
                },
                onCardChange = { newCard -> card = newCard },
                isCardSavable = card.isValid(),
                isSheetVisible = isSheetVisible,
                onChangeSheetVisible = { isOpen -> isSheetVisible = isOpen },
            )
        }
    }

    companion object {
        const val EXTRA_CARD = "card"
    }
}

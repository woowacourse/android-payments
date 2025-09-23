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
import woowacourse.payments.ui.model.CardScreenCategory
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.util.getParcelableExtraCompat

class EditCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val initCard = requireNotNull(intent.getParcelableExtraCompat<CardUiModel>(EXTRA_CARD)).toDomain()
            var card by remember { mutableStateOf(initCard) }
            var isSheetVisible by remember { mutableStateOf(false) }
            CardScreen(
                card = card,
                cardScreenCategory = CardScreenCategory.Edit,
                onBackClick = { finish() },
                onSaveClick = {
                    val resultIntent = Intent()
                    resultIntent.putExtra(EXTRA_CARD, card.toUiModel())
                    setResult(RESULT_OK, resultIntent)
                    finish()
                },
                onCardChange = { newCard -> card = newCard },
                isSheetVisible = isSheetVisible,
                onChangeSheetVisible = { isOpen -> isSheetVisible = isOpen },
                isCardSavable = card.isValid() && card != initCard,
            )
        }
    }

    companion object {
        const val EXTRA_CARD = "EXTRA_CARD"
    }
}

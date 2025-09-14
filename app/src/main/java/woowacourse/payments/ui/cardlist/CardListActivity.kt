package woowacourse.payments.ui.cardlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateListOf
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import woowacourse.payments.ui.ExtraKeys
import woowacourse.payments.ui.addcard.AddCardActivity
import woowacourse.payments.ui.format.ExpirationDateFormat
import woowacourse.payments.ui.getParcelableExtraCompat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import java.time.YearMonth

class CardListActivity : ComponentActivity() {
    private val cards = mutableStateListOf<CardUiModel>()
    private val activityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            handleAddCardResult(result)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardListContents(
                cards = cards,
                navigateToAddCard = { navigateToAddCard() },
            )
        }
    }

    private fun navigateToAddCard() {
        activityResultLauncher.launch(AddCardActivity.intent(this))
    }

    private fun Intent.toCardOrNull(): Card? =
        runCatching {
            getParcelableExtraCompat<CardUiModel>(ExtraKeys.CARD_KEY)?.let { card: CardUiModel ->
                val yearMonth =
                    YearMonth.parse(card.expirationDate, ExpirationDateFormat.formatPattern)
                Card(
                    CardNumber(card.cardNumber),
                    ExpirationDate(yearMonth),
                    CardholderName(card.cardholderName),
                    Passcode(card.passcode),
                )
            }
        }.getOrNull()

    private fun handleAddCardResult(result: ActivityResult) {
        if (result.resultCode != RESULT_OK) return
        val card: Card = result.data?.toCardOrNull() ?: return
        cards.add(card.toUiModel())
    }
}

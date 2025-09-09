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
import woowacourse.payments.ui.formatter.ExpirationDateFormat
import java.time.YearMonth

class CardListActivity : ComponentActivity() {
    private val cards = mutableStateListOf<Card>()
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
            val cardNumber: String = getStringExtra(ExtraKeys.CARD_NUMBER_KEY) ?: return null
            val cardholderName: String =
                getStringExtra(ExtraKeys.CARDHOLDER_NAME_KEY) ?: return null
            val expirationDate: YearMonth =
                getStringExtra(ExtraKeys.CARD_EXPIRATION_DATE_KEY)?.let { yearMonth: String ->
                    YearMonth.parse(yearMonth, ExpirationDateFormat.formatPattern)
                } ?: return null
            val passcode: String = getStringExtra(ExtraKeys.CARD_PASSCODE_KEY) ?: return null

            Card(
                CardNumber(cardNumber),
                ExpirationDate(expirationDate),
                CardholderName(cardholderName),
                Passcode(passcode),
            )
        }.getOrNull()

    private fun handleAddCardResult(result: ActivityResult) {
        if (result.resultCode != RESULT_OK) return
        val card: Card = result.data?.toCardOrNull() ?: return
        cards.add(card)
    }
}

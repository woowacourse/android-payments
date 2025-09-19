package woowacourse.payments.ui.cardlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import woowacourse.payments.ui.addcard.AddCardActivity
import woowacourse.payments.ui.common.ExtraKeys
import woowacourse.payments.ui.common.getParcelableExtraCompat
import woowacourse.payments.ui.format.ExpirationDateFormat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import java.time.YearMonth

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                val cards = remember { mutableStateListOf<CardUiModel>() }

                val launcher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                        if (result.resultCode == RESULT_OK) {
                            result.data?.toCardOrNull()?.let { card: Card ->
                                cards.add(card.toUiModel())
                            }
                        }
                    }

                CardListScreen(cards) { launcher.launch(AddCardActivity.intent(this)) }
            }
        }
    }

    private fun Intent.toCardOrNull(): Card? =
        runCatching {
            getParcelableExtraCompat<CardUiModel>(ExtraKeys.CARD_KEY)?.let { card: CardUiModel ->
                val yearMonth: YearMonth =
                    YearMonth.parse(card.expirationDate, ExpirationDateFormat.formatPattern)

                Card(
                    CardNumber(card.cardNumber),
                    ExpirationDate(yearMonth),
                    CardholderName(card.cardholderName),
                    Passcode(card.passcode),
                    card.cardCompany.company,
                )
            }
        }.getOrNull()
}

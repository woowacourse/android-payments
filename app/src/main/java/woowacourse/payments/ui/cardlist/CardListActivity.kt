package woowacourse.payments.ui.cardlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import woowacourse.payments.ui.ExtraKeys
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import java.time.YearMonth

class CardListActivity : ComponentActivity() {
    private val cards = mutableListOf<Card>()
    val activityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            handleNewCardResult(result)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidpaymentsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CardListTopBar {
                            activityResultLauncher.launch(NewCardActivity.intent(this))
                        }
                    },
                ) { innerPadding: PaddingValues ->
                }
            }
        }
    }

    fun handleNewCardResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            runCatching {
                result.data?.let { data: Intent ->
                    val cardNumber: CardNumber =
                        data.getStringExtra(ExtraKeys.CARD_NUMBER_KEY)?.let(::CardNumber) ?: return
                    val cardholderName: CardholderName =
                        data.getStringExtra(ExtraKeys.CARDHOLDER_NAME_KEY)?.let(::CardholderName)
                            ?: return
                    val expirationDateValue: YearMonth =
                        data
                            .getStringExtra(ExtraKeys.CARD_EXPIRATION_DATE_KEY)
                            ?.let(YearMonth::parse) ?: return
                    val expirationDate = ExpirationDate(expirationDateValue)
                    val passcode: Passcode =
                        data.getStringExtra(ExtraKeys.CARD_PASSCODE_KEY)?.let(::Passcode) ?: return
                    Card(cardNumber, cardholderName, expirationDate, passcode)
                } ?: return
            }.onSuccess { card: Card -> cards.add(card) }.onFailure { error: Throwable ->
                Toast.makeText(this, "카드를 추가하지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardListActivityPreview() {
    AndroidpaymentsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { CardListTopBar() },
        ) { innerPadding: PaddingValues ->
        }
    }
}

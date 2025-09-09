package woowacourse.payments.ui.cardlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import woowacourse.payments.ui.ExtraKeys
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.newcard.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CardListActivity : ComponentActivity() {
    private val cards = mutableStateListOf<Card>()
    private val activityResultLauncher: ActivityResultLauncher<Intent> =
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
                            navigateToAddCard()
                        }
                    },
                ) { innerPadding: PaddingValues ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(36.dp),
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(innerPadding),
                    ) {
                        if (cards.isEmpty()) {
                            Text(
                                text = "새로운 카드를 등록해주세요",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 32.dp),
                            )
                        }

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(36.dp),
                        ) {
                            items(
                                count = cards.size,
                            ) { index: Int ->
                                val card: Card = cards[index]
                                PaymentCard(
                                    cardNumber = card.number.value,
                                    expirationDate = card.expirationDate.expirationYearMonth,
                                    cardholderName = card.holderName.value,
                                )
                            }
                        }

                        if (cards.size <= 1) {
                            AddCardButton { navigateToAddCard() }
                        }
                    }
                }
            }
        }
    }

    private fun navigateToAddCard() {
        activityResultLauncher.launch(NewCardActivity.intent(this))
    }

    private fun handleNewCardResult(result: ActivityResult) {
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
                            ?.let { value: String ->
                                YearMonth.parse(
                                    value,
                                    DateTimeFormatter.ofPattern("MMyy"),
                                )
                            } ?: return
                    val expirationDate = ExpirationDate(expirationDateValue)
                    val passcode: Passcode =
                        data.getStringExtra(ExtraKeys.CARD_PASSCODE_KEY)?.let(::Passcode) ?: return
                    Card(cardNumber, cardholderName, expirationDate, passcode)
                } ?: return
            }.onSuccess { card: Card -> cards.add(card) }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun CardListActivityPreview() {
    AndroidpaymentsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { CardListTopBar() },
        ) { innerPadding: PaddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(36.dp),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
            ) {
                Text(
                    text = "새로운 카드를 등록해주세요",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 32.dp),
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(36.dp),
                ) {
                    item {
                        PaymentCard(
                            cardNumber = "1234 - 1234 - **** - ****",
                            cardholderName = "CREW 1",
                            expirationDate = YearMonth.of(2034, 12),
                        )
                    }
                    item {
                        PaymentCard(
                            cardNumber = "1234 - 1234 - **** - ****",
                            cardholderName = "CREW 2",
                            expirationDate = YearMonth.of(2034, 12),
                        )
                    }
                }

                AddCardButton(Modifier.padding())
            }
        }
    }
}

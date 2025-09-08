package woowacourse.payments.ui.cardlist

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardDigit
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardHolderName
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.ui.cardlist.components.AddPaymentCard
import woowacourse.payments.ui.cardlist.components.CardListTopBar
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.newcard.NewCardActivity
import woowacourse.payments.ui.util.getParcelableCompat
import java.time.YearMonth

@Composable
fun CardListScreen(
    cards: List<Card> = emptyList(),
    onCardAdded: (Card) -> Unit = {},
) {
    val context = LocalContext.current
    val newCardLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data
                    ?.getParcelableCompat<Card>("new_card")
                    ?.let(onCardAdded)
            }
        }
    val launchNewCard: () -> Unit = { newCardLauncher.launch(NewCardActivity.newIntent(context)) }

    Scaffold(
        topBar = {
            CardListTopBar(
                onAddClick = launchNewCard,
                showAddButton = cards.size > 1,
            )
        },
    ) { innerPadding: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding() + 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp),
        ) {
            if (cards.isEmpty()) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "새로운 카드를 등록해주세요",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            cards.forEach { card: Card ->
                PaymentCard(card = card)
            }
            if (cards.size <= 1) {
                AddPaymentCard(onAddClick = launchNewCard)
            }
        }
    }
}

@Preview(name = "카드 0개")
@Composable
private fun CardListScreenPreview1() {
    CardListScreen()
}

@Preview(name = "카드 1개")
@Composable
private fun CardListScreenPreview2() {
    CardListScreen(
        cards =
            listOf(
                Card(
                    number =
                        CardNumber(
                            listOf(1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4)
                                .map(::CardDigit),
                        ),
                    expirationDate = CardExpirationDate(YearMonth.of(2025, 9)),
                    password = CardPassword("0000"),
                    holderName = CardHolderName("CREW"),
                ),
            ),
    )
}

@Preview(name = "카드 n개")
@Composable
private fun CardListScreenPreview3() {
    CardListScreen(
        cards =
            List(3) {
                Card(
                    number =
                        CardNumber(
                            listOf(1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4)
                                .map(::CardDigit),
                        ),
                    expirationDate = CardExpirationDate(YearMonth.of(2025, 9)),
                    password = CardPassword("0000"),
                    holderName = CardHolderName("CREW"),
                )
            },
    )
}

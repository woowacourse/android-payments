package woowacourse.payments.ui.cardlist

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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.newcard.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import java.time.YearMonth

@Suppress("ktlint:standard:function-naming")
@Composable
fun CardListContents(
    cards: SnapshotStateList<Card>,
    navigateToAddCard: () -> Unit,
) {
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
                        text = stringResource(R.string.card_list_add_card_guide_text),
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

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun CardListContentsPreview() {
    AndroidpaymentsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CardListTopBar()
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
                Text(
                    text = stringResource(R.string.card_list_add_card_guide_text),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 32.dp),
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(36.dp),
                ) {
                    item {
                        PaymentCard(
                            cardNumber = "1234123412341234",
                            cardholderName = "CREW 1",
                            expirationDate = YearMonth.of(2034, 12),
                        )
                    }
                    item {
                        PaymentCard(
                            cardNumber = "5678567856785678",
                            cardholderName = "CREW 2",
                            expirationDate = YearMonth.of(2034, 12),
                        )
                    }
                }

                AddCardButton()
            }
        }
    }
}

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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import woowacourse.payments.ui.common.PaymentCard
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import java.time.YearMonth

@Composable
fun CardListScreen(
    cards: SnapshotStateList<CardUiModel>,
    navigateToAddCard: () -> Unit,
) {
    AndroidpaymentsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { CardListTopBar(cards) { navigateToAddCard() } },
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
                    items(count = cards.size) { index: Int ->
                        PaymentCard(card = cards[index])
                    }
                }

                if (cards.size <= 1) {
                    AddCardButton { navigateToAddCard() }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardListScreenWithNoCardsPreview() {
    CardListScreen(
        cards = remember { mutableStateListOf() },
        navigateToAddCard = {},
    )
}

@Preview(showBackground = true)
@Composable
fun CardListScreenWithOneCardPreview() {
    CardListScreen(
        cards =
            remember {
                mutableStateListOf(
                    Card(
                        CardNumber("1234123412341234"),
                        ExpirationDate(YearMonth.of(2034, 12)),
                        CardholderName("디랙"),
                        Passcode("1234"),
                    ).toUiModel(),
                )
            },
        navigateToAddCard = {},
    )
}

@Preview(showBackground = true)
@Composable
fun CardListScreenWithTwoCardsPreview() {
    CardListScreen(
        cards =
            remember {
                mutableStateListOf(
                    Card(
                        CardNumber("1234123412341234"),
                        ExpirationDate(YearMonth.of(2034, 12)),
                        CardholderName("디랙"),
                        Passcode("1234"),
                    ).toUiModel(),
                    Card(
                        CardNumber("1234123412341234"),
                        ExpirationDate(YearMonth.of(2034, 12)),
                        CardholderName("디랙"),
                        Passcode("1234"),
                    ).toUiModel(),
                )
            },
        navigateToAddCard = {},
    )
}

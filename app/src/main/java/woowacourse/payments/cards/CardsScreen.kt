package woowacourse.payments.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.cards.component.CardsTopBar
import woowacourse.payments.cards.component.EmptyCard
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.util.PaymentCard

@Composable
fun CardsScreen(paymentCards: List<Card>) {
    val cards: SnapshotStateList<Card> = remember { paymentCards.toMutableStateList() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CardsTopBar(
                onAddClick = {},
                modifier = Modifier.padding(),
                isAddable = cards.size > 1,
            )
        },
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(top = 12.dp),
        ) {
            if (cards.isEmpty()) {
                Text(
                    "새로운 카드를 등록해주세요.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 20.dp, bottom = 32.dp),
                )
            }

            cards.forEach { card: Card ->
                PaymentCard(card = card)
                Spacer(modifier = Modifier.height(32.dp))
            }

            if (cards.size <= 1) {
                EmptyCard()
            }
        }
    }
}

@Preview
@Composable
private fun CardsScreenPreview() {
    AndroidpaymentsTheme {
        CardsScreen(
            listOf(
                Card(
                    cardNumber = CardNumber("1234567812345678"),
                    expiredDate = ExpiredDate.of(4, 26)!!,
                    ownerName = OwnerName("크림"),
                    password = "1234",
                ),
            ),
        )
    }
}

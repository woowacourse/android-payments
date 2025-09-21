package woowacourse.payments.ui.cards.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.toBankViewType
import java.time.YearMonth

@Composable
fun RegisteredCards(
    modifier: Modifier = Modifier,
    cards: List<Card>,
) {
    Column(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        cards.forEach { card ->

            PaymentCard(
                bankViewType = card.bankType.toBankViewType(),
                modifier = Modifier.padding(bottom = 36.dp),
                card = card,
            )
        }
    }
}

@Composable
@Preview
private fun RegisteredCardsPreview() {
    val card =
        Card
            .create(
                cardNumber = "1234123412341234",
                expiryDate = YearMonth.of(2035, 12),
                cardOwner = "쥐돌킹",
                password = "1234",
                bankType = BankType.BC,
            ).getOrNull()
    RegisteredCards(cards = List(3) { card!! })
}

package woowacourse.payments.cards.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.BankType
import woowacourse.payments.Card
import woowacourse.payments.ui.component.PaymentCard

@Composable
fun MultipleCardContent(
    cards: List<Card>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        cards.forEach { card: Card ->
            PaymentCard(
                card = card,
                modifier = Modifier.padding(bottom = 36.dp),
            )
        }
    }
}

@Preview
@Composable
private fun MultipleCardContentPreview() {
    MultipleCardContent(
        cards =
            listOf(
                Card(
                    number = "1234".repeat(4),
                    owner = "CREW",
                    expiredDate = "0421",
                    bankType = BankType.BC,
                ),
                Card(
                    number = "1234".repeat(4),
                    owner = "CREW",
                    expiredDate = "0421",
                    bankType = BankType.KB,
                ),
                Card(
                    number = "1234".repeat(4),
                    owner = "CREW",
                    expiredDate = "0421",
                    bankType = BankType.HANA,
                ),
            ),
    )
}

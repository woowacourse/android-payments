package woowacourse.payments.cards.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.BankType
import woowacourse.payments.CardUiModel
import woowacourse.payments.ui.component.PaymentCard

@Composable
fun OneCardContent(
    card: CardUiModel,
    addCard: () -> Unit,
    onClickCard: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        PaymentCard(
            card = card,
            onClick = { onClickCard(card) },
            modifier = Modifier.padding(top = 12.dp),
        )
        PaymentCardAdditionButton(
            onClick = addCard,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OneCardContentPreview() {
    OneCardContent(
        card =
            CardUiModel(
                number = "1234".repeat(4),
                holder = "CREW",
                expiredDate = "0421",
                bankType = BankType.HANA,
            ),
        addCard = {},
        onClickCard = {},
    )
}

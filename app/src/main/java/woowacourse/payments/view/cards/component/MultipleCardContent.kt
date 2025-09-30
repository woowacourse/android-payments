package woowacourse.payments.view.cards.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.view.ui.component.PaymentCard
import woowacourse.payments.view.ui.model.BankTypeUiModel
import woowacourse.payments.view.ui.model.CardUiModel

@Composable
fun MultipleCardContent(
    cards: List<CardUiModel>,
    onClickCard: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(cards) { card: CardUiModel ->
            PaymentCard(
                onClick = { onClickCard(card) },
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
                CardUiModel(
                    number = "1234".repeat(4),
                    holder = "CREW",
                    expiredDate = "0421",
                    bankType = BankTypeUiModel.BC,
                ),
                CardUiModel(
                    number = "1234".repeat(4),
                    holder = "CREW",
                    expiredDate = "0421",
                    bankType = BankTypeUiModel.KB,
                ),
                CardUiModel(
                    number = "1234".repeat(4),
                    holder = "CREW",
                    expiredDate = "0421",
                    bankType = BankTypeUiModel.HANA,
                ),
            ),
        onClickCard = {},
    )
}

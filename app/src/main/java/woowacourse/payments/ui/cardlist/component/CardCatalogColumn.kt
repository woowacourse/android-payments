package woowacourse.payments.ui.cardlist.component

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.cardlist.state.CardListUiStatus
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel
import java.time.YearMonth

@Composable
fun CardCatalogColumn(
    cardListStatus: CardListUiStatus,
    onAddCard: () -> Unit,
    onEditCard: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Spacer(modifier = Modifier.height(32.dp)) }
        when (cardListStatus) {
            is CardListUiStatus.EmptyCardList -> {
                item { InformationText() }
                item {
                    EmptyPaymentCard {
                        onAddCard()
                    }
                }
            }

            is CardListUiStatus.OneCardList -> {
                item {
                    PaymentCard(
                        cardUiModel = cardListStatus.card,
                        onEditCard = { onEditCard(it) })
                }
                item { EmptyPaymentCard { onAddCard() } }
            }

            is CardListUiStatus.MultiCardList -> {
                items(
                    items = cardListStatus.card,
                    key = { card -> "${card.number}" }
                ) { card ->
                    PaymentCard(
                        cardUiModel = card,
                        onEditCard = { onEditCard(it) })
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "카드의 개수가 0일때")
@Composable
private fun CardCatalogColumnPreview0() {
    CardCatalogColumn(
        cardListStatus = CardListUiStatus.EmptyCardList,
        onAddCard = {},
        onEditCard = {},
    )
}

@Preview(showBackground = true, name = "카드의 개수가 1일때")
@Composable
private fun CardCatalogColumnPreview1() {
    val card =
        CardUiModel(
            number = "1234567890123456",
            ownerName = "Hwang Chaewon",
            expiredDate = "0230",
            password = "1234",
            cardCompanyUiModel = CardCompanyUiModel.Default,
        )

    CardCatalogColumn(
        cardListStatus = CardListUiStatus.OneCardList(card),
        onAddCard = {},
        onEditCard = {},
    )
}

@Preview(name = "카드가 2개일 때", showBackground = true)
@Composable
private fun CardCatalogColumnPreview2() {
    val cards = listOf(
        CardUiModel(
            number = "1234567890123456",
            ownerName = "Hwang Chaewon",
            expiredDate = "0230",
            password = "1234",
            cardCompanyUiModel = CardCompanyUiModel.Default,
        ),
        CardUiModel(
            number = "1234567890123456",
            ownerName = "Hwang Chaewon",
            expiredDate = "0230",
            password = "1234",
            cardCompanyUiModel = CardCompanyUiModel.Default,
        )
    )
    CardCatalogColumn(
        cardListStatus = CardListUiStatus.MultiCardList(cards),
        onAddCard = {},
        onEditCard = {},
    )
}


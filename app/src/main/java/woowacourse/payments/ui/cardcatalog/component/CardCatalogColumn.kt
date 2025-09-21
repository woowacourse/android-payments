package woowacourse.payments.ui.cardcatalog.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import java.time.YearMonth

@Composable
fun CardCatalogColumn(
    cards: List<Card> = emptyList(),
    onClickAddCard: () -> Unit,
    onEditCard: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Spacer(modifier = Modifier.height(32.dp)) }
        when {
            cards.isEmpty() -> item { AddNewCardInformationText() }
            else -> item { PaymentCards(cards, { card -> onEditCard(card) }) }
        }
        if (cards.size < 2) item { EmptyPaymentCard(onClickAddCard) }
    }
}


@Preview(showBackground = true)
@Composable
private fun CardCatalogColumnPreview1() {
    val cards = listOf(
        Card(
            number = CardNumber("1234567890123456"),
            ownerName = OwnerName("Hwang Chaewon"),
            expirationDate = ExpirationDate(YearMonth.now().plusYears(1)),
            password = Password("1234"),
            cardCompany = CardCompany.BC,
        )
    )
    CardCatalogColumn(cards = cards, onClickAddCard = {}, {})
}

@Preview(name = "카드가 2개일 때", showBackground = true)
@Composable
private fun CardCatalogColumnPreview2() {
    val cards = listOf(
        Card(
            number = CardNumber("1234567890123456"),
            ownerName = OwnerName("Hwang Chaewon"),
            expirationDate = ExpirationDate(YearMonth.now().plusYears(1)),
            password = Password("1234"),
            cardCompany = CardCompany.BC,
        ),
        Card(
            number = CardNumber("1234567890123456"),
            ownerName = OwnerName("Hwang Chaewon"),
            expirationDate = ExpirationDate(YearMonth.now().plusYears(1)),
            password = Password("1234"),
            cardCompany = CardCompany.BC,
        )
    )
    CardCatalogColumn(cards = cards, onClickAddCard = {}, {})
}


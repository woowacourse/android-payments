package woowacourse.payments.ui.cards.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.mapper.toUiModel
import woowacourse.payments.ui.theme.Gray300

@Composable
fun PaymentCardsContent(
    modifier: Modifier = Modifier,
    paymentCards: List<PaymentCardUiModel>,
    onAddCard: () -> Unit,
    onEditCard: (String) -> Unit,
) {
    when (paymentCards.size) {
        0 -> EmptyCard(modifier = modifier, onAddCard = onAddCard)
        1 ->
            SingleCard(
                modifier = modifier,
                paymentCard = paymentCards.first(),
                onAddCard = onAddCard,
                onEditCard = onEditCard,
            )

        else ->
            MultiCards(
                modifier = modifier,
                paymentCards = paymentCards,
                onEditCard = onEditCard,
            )
    }
}

@Composable
private fun EmptyCard(
    modifier: Modifier = Modifier,
    onAddCard: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.add_payment_card_guide),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Gray300,
            modifier =
                modifier
                    .padding(vertical = 32.dp),
        )
        AddCard(onAddClick = onAddCard)
    }
}

@Composable
private fun SingleCard(
    paymentCard: PaymentCardUiModel,
    onAddCard: () -> Unit,
    onEditCard: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PaymentCard(
            paymentCard = paymentCard,
            onSelectBank = {},
            onEditCard = { onEditCard(paymentCard.id) },
            modifier = Modifier.padding(top = 12.dp, bottom = 36.dp),
        )
        AddCard(onAddClick = onAddCard)
    }
}

@Composable
private fun MultiCards(
    paymentCards: List<PaymentCardUiModel>,
    onEditCard: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            items = paymentCards,
            key = { it.id },
        ) { card ->
            PaymentCard(
                paymentCard = card,
                onSelectBank = {},
                onEditCard = { onEditCard(card.id) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyCardPreview() {
    EmptyCard(onAddCard = {})
}

@Preview(showBackground = true)
@Composable
private fun SingleCardPreview() {
    SingleCard(
        onAddCard = {},
        onEditCard = {},
        paymentCard =
            PaymentCardUiModel(
                "0",
                "1234567812345678",
                "0511",
                "minjeong",
                BankType.KB.toUiModel(),
            ),
    )
}

@Preview(showBackground = true)
@Composable
private fun MultiCardsPreview() {
    MultiCards(
        paymentCards =
            listOf(
                PaymentCardUiModel(
                    "1",
                    "1234123456785678",
                    "1215",
                    "minjeong",
                    BankType.SHINHAN.toUiModel(),
                ),
                PaymentCardUiModel(
                    "2",
                    "1111222233334444",
                    "1234",
                    "junseo",
                    BankType.HYUNDAI.toUiModel(),
                ),
            ),
        {},
    )
}

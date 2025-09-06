package woowacourse.payments.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.component.PaymentCard
import woowacourse.payments.component.RegisteredCard
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardType
import woowacourse.payments.preview.CardsPreviewParameterProvider
import woowacourse.payments.preview.OneCardPreviewParameterProvider

@Composable
fun CardsScreen(
    cards: List<Card>,
    onClickCard: (CardType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            cards.isEmpty() -> {
                Text(
                    text = stringResource(R.string.card_list_empty),
                    fontSize = 22.sp,
                    modifier = Modifier.padding(top = 50.dp)
                )

                PaymentCard(
                    cardType = CardType.EMPTY,
                    onClick = onClickCard,
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.content_description_card_list_empty),
                        )
                    },
                    modifier = Modifier
                        .padding(top = 18.dp)
                )
            }

            cards.size == 1 -> {
                PaymentCard(
                    cardType = CardType.REGISTERED,
                    onClick = {},
                    content = { RegisteredCard(cards[0]) },
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .shadow(8.dp),
                )
                PaymentCard(
                    cardType = CardType.EMPTY,
                    onClick = { onClickCard(CardType.EMPTY) },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.content_description_card_list_empty),
                        )
                    },
                    modifier = Modifier
                        .padding(top = 30.dp)
                )
            }

            cards.size > 1 -> {
                repeat(cards.size) {
                    PaymentCard(
                        cardType = CardType.REGISTERED,
                        onClick = {},
                        content = { RegisteredCard(cards[it]) },
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .shadow(8.dp),
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardScreenPreview() {
    CardsScreen(emptyList(), {})
}

@Composable
@Preview(showBackground = true)
fun OneCardScreenPreview(
    @PreviewParameter(OneCardPreviewParameterProvider::class) card: Card
) {
    CardsScreen(listOf(card), {})
}

@Composable
@Preview(showBackground = true)
fun CardsScreenPreview(
    @PreviewParameter(CardsPreviewParameterProvider::class) cards: List<Card>
) {
    CardsScreen(cards, {})
}


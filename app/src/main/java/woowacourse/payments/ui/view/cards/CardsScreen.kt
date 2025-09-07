package woowacourse.payments.ui.view.cards

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.component.RegisteredCard
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.core.CardType
import woowacourse.payments.ui.preview.CardsPreviewParameterProvider
import woowacourse.payments.ui.preview.OneCardPreviewParameterProvider

@Composable
fun CardsScreen(
    cards: List<Card>,
    uiEvent: CardScreenUiEvent,
    onClickCard: (CardType) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val toastMessage = stringResource(R.string.card_list_add_new_card)

    when (uiEvent) {
        CardScreenUiEvent.CompleteAddCard -> {
            Toast.makeText(
                context,
                toastMessage,
                Toast.LENGTH_SHORT
            ).show()
        }

        CardScreenUiEvent.Idle -> Unit
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (CardsScreenVisibility.of(cards)) {
            CardsScreenVisibility.EMPTY -> {
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

            CardsScreenVisibility.SINGLE -> {
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

            CardsScreenVisibility.MULTIPLE -> {
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
    CardsScreen(emptyList(), CardScreenUiEvent.Idle, {})
}

@Composable
@Preview(showBackground = true)
fun OneCardScreenPreview(
    @PreviewParameter(OneCardPreviewParameterProvider::class) card: Card
) {
    CardsScreen(listOf(card), CardScreenUiEvent.Idle, {})
}

@Composable
@Preview(showBackground = true)
fun CardsScreenPreview(
    @PreviewParameter(CardsPreviewParameterProvider::class) cards: List<Card>
) {
    CardsScreen(cards, CardScreenUiEvent.Idle, {})
}


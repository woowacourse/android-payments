package woowacourse.payments.ui.cardwallet.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.cardwallet.model.CardWalletState
import woowacourse.payments.ui.common.model.CardUiModel

@Composable
fun CardWalletContent(
    cards: List<CardUiModel>,
    cardWalletState: CardWalletState,
    navigateToNewCard: () -> Unit,
) {
    when (cardWalletState) {
        CardWalletState.EMPTY -> {
            Spacer(Modifier.height(32.dp))
            EmptyGuide()
            Spacer(Modifier.height(32.dp))
            NewCardPlaceholder(
                modifier =
                    Modifier
                        .width(208.dp)
                        .height(124.dp),
                onClick = { navigateToNewCard() },
            )
        }

        CardWalletState.SINGLE -> {
            CardWalletCards(cards)
            NewCardPlaceholder(
                modifier =
                    Modifier
                        .width(208.dp)
                        .height(124.dp),
                onClick = { navigateToNewCard() },
            )
        }

        CardWalletState.MULTIPLE -> {
            CardWalletCards(cards)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardWalletContentPreview() {
    AndroidpaymentsTheme {
        val cards =
            listOf<CardUiModel>(
                CardUiModel("1234123412341234", "0511", "공백"),
                CardUiModel("4321432143214321", "0928", "비비"),
            )
        CardWalletContent(
            cards = cards,
            cardWalletState = CardWalletState.MULTIPLE,
            navigateToNewCard = {},
        )
    }
}

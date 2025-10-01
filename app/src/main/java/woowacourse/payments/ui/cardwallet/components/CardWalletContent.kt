package woowacourse.payments.ui.cardwallet.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.cardwallet.model.CardWalletState
import woowacourse.payments.ui.common.model.CardUiModel

@Composable
fun CardWalletContent(
    cards: List<CardUiModel>,
    cardWalletState: CardWalletState,
    navigateToNewCard: () -> Unit,
    navigateToEditCard: (CardUiModel) -> Unit,
) {
    when (cardWalletState) {
        CardWalletState.EMPTY -> EmptyWallet(navigateToNewCard = navigateToNewCard)
        CardWalletState.SINGLE ->
            SingleCardWallet(
                cards = cards,
                navigateToNewCard = navigateToNewCard,
                navigateToEditCard = navigateToEditCard,
            )

        CardWalletState.MULTIPLE ->
            MultipleCardWallet(
                cards = cards,
                navigateToEditCard = navigateToEditCard,
            )
    }
}

@Composable
private fun EmptyWallet(navigateToNewCard: () -> Unit) {
    Column {
        Spacer(Modifier.height(32.dp))
        EmptyGuide()
        Spacer(Modifier.height(32.dp))
        NewCardPlaceholder(
            modifier =
                Modifier
                    .width(208.dp)
                    .height(124.dp),
            onClick = navigateToNewCard,
        )
    }
}

@Composable
private fun SingleCardWallet(
    cards: List<CardUiModel>,
    navigateToNewCard: () -> Unit,
    navigateToEditCard: (CardUiModel) -> Unit,
) {
    Column {
        CardWalletCards(cards, navigateToEditCard)
        NewCardPlaceholder(
            modifier =
                Modifier
                    .width(208.dp)
                    .height(124.dp),
            onClick = navigateToNewCard,
        )
    }
}

@Composable
private fun MultipleCardWallet(
    cards: List<CardUiModel>,
    navigateToEditCard: (CardUiModel) -> Unit,
) {
    CardWalletCards(cards, navigateToEditCard)
}

@Preview(showBackground = true)
@Composable
private fun CardWalletContentPreview() {
    AndroidpaymentsTheme {
        val cards =
            listOf(
                CardUiModel(1L, "1234123412341234", "0511", "공백", BankType.HYUNDAI),
                CardUiModel(2L, "4321432143214321", "0928", "비비", BankType.KAKAO_BANK),
            )
        CardWalletContent(
            cards = cards,
            cardWalletState = CardWalletState.MULTIPLE,
            navigateToNewCard = {},
            navigateToEditCard = {},
        )
    }
}

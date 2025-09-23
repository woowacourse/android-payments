package woowacourse.payments.ui.cardwallet.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.common.model.CardUiModel

@Composable
fun CardWalletCards(cards: List<CardUiModel>) {
    Spacer(Modifier.height(12.dp))
    cards.forEach { card ->
        PaymentCard(card = card)
        Spacer(Modifier.height(36.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun WalletCardsPreview() {
    AndroidpaymentsTheme {
        val cards =
            listOf<CardUiModel>(
                CardUiModel("1234123412341234", "0511", "공백", BankType.HYUNDAI),
                CardUiModel("4321432143214321", "0928", "비비", BankType.KAKAO_BANK),
            )
        CardWalletCards(cards = cards)
    }
}

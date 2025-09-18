package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.ui.components.PaymentCards
import woowacourse.payments.ui.components.PaymentTopBar
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentScreen(
    cards: List<CardUiModel>,
    onAddCardClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PaymentTopBar(
                modifier = Modifier.fillMaxWidth(),
                onAddClick = onAddCardClick.takeIf { cards.size >= 2 },
            )
        },
    ) { innerPadding ->
        PaymentCards(
            cards = cards,
            onAddCardClick = onAddCardClick,
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
        )
    }
}

@Preview(name = "카드가 없는 경우")
@Composable
private fun PaymentScreenEmptyPreview() {
    AndroidpaymentsTheme {
        PaymentScreen(cards = emptyList(), onAddCardClick = {})
    }
}

@Preview(name = "카드가 한개 존재하는 경우")
@Composable
private fun PaymentScreenOneCardPreview() {
    AndroidpaymentsTheme {
        val sampleCard =
            CardUiModel(
                cardCompany = CardCompanyType.BC.toUiModel(),
                cardNumber = "1111 - 2222 - 3333 - 4444",
                expirationDate = "04 / 21",
                userName = "CREW",
                password = "1234",
            )
        PaymentScreen(cards = listOf(sampleCard), onAddCardClick = {})
    }
}

@Preview(name = "카드가 3개 이상 존재하는 경우")
@Composable
private fun PaymentScreenThreeCardsPreview() {
    val sampleCard =
        CardUiModel(
            cardCompany = CardCompanyType.BC.toUiModel(),
            cardNumber = "1111 - 2222 - 3333 - 4444",
            expirationDate = "04 / 21",
            userName = "CREW",
            password = "1234",
        )
    AndroidpaymentsTheme {
        val cards =
            listOf(
                sampleCard,
                sampleCard,
                sampleCard,
            )
        PaymentScreen(cards = cards, onAddCardClick = {})
    }
}

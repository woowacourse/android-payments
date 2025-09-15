package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.components.PaymentCards
import woowacourse.payments.ui.components.PaymentTopBar
import woowacourse.payments.ui.model.CardUiModel
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
    AndroidpaymentsTheme {
        val cards =
            listOf(
                CardUiModel("1111 - 2222 - 3333 - 4444", "04 / 21", "CREW", "1234"),
                CardUiModel("5555 - 6666 - 7777 - 8888", "05 / 22", "GAHYUN", "5678"),
                CardUiModel("9999 - 0000 - 1111 - 2222", "06 / 23", "ANDY", "9012"),
            )
        PaymentScreen(cards = cards, onAddCardClick = {})
    }
}

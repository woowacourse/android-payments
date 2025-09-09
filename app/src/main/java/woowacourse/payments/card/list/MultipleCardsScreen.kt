package woowacourse.payments.card.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.card.component.PaymentCard
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun MultipleCardsScreen(
    cards: List<CardUiModel>,
    onAddNewCardClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        cards.forEach { card ->
            PaymentCard(
                cardNumber = card.number,
                expiredDate = card.expirationDate,
                ownerName = card.cardHolderName,
            )
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Preview
@Composable
fun MultipleCardsScreenPreview() {
    val cards =
        listOf(
            CardUiModel(
                number = "1234567890123456",
                expirationDate = "11/24",
                cardHolderName = "TAMA ONE",
            ),
            CardUiModel(
                number = "1234567890123456",
                expirationDate = "11/24",
                cardHolderName = "TAMA TWO",
            ),
            CardUiModel(
                number = "1234567890123456",
                expirationDate = "11/24",
                cardHolderName = "TAMA THREE",
            ),
        )
    AndroidpaymentsTheme {
        MultipleCardsScreen(
            cards = cards,
            onAddNewCardClick = {},
        )
    }
}

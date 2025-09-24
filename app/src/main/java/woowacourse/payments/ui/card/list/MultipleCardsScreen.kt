package woowacourse.payments.ui.card.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.card.component.PaymentCard
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun MultipleCardsScreen(cards: List<CardUiModel>, onEditCardClick: (CardUiModel) -> Unit) {
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
                bankName = card.bankName,
                backgroundColor = Color(card.bankColor),
                onClick = { onEditCardClick(card) },
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
                id = 1,
                number = "1234 - 5678 - **** - ****",
                expirationDate = "11/24",
                cardHolderName = "TAMA ONE",
                bankName = "국민카드",
                bankColor = 0xFFFBC02D,
            ),
            CardUiModel(
                id = 2,
                number = "1234 - 5678 - **** - ****",
                expirationDate = "11/24",
                cardHolderName = "TAMA TWO",
                bankName = "신한카드",
                bankColor = 0xFFFBC02D,
            ),
            CardUiModel(
                id = 3,
                number = "1234 - 5678 - **** - ****",
                expirationDate = "11/24",
                cardHolderName = "TAMA THREE",
                bankName = "카카오뱅크",
                bankColor = 0xFFFBC02D,
            ),
        )
    AndroidpaymentsTheme {
        MultipleCardsScreen(
            cards = cards,
            onEditCardClick = {},
        )
    }
}

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
fun MultipleCardsScreen(cards: List<CardUiModel>) {
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
                number = "1234 - 5678 - **** - ****",
                expirationDate = "11/24",
                cardHolderName = "TAMA ONE",
                bankName = "국민카드",
                bankColor = Color(0xFFFBC02D).toArgb(),
            ),
            CardUiModel(
                number = "1234 - 5678 - **** - ****",
                expirationDate = "11/24",
                cardHolderName = "TAMA TWO",
                bankName = "신한카드",
                bankColor = Color(0xFF1565C0).toArgb(),
            ),
            CardUiModel(
                number = "1234 - 5678 - **** - ****",
                expirationDate = "11/24",
                cardHolderName = "TAMA THREE",
                bankName = "카카오뱅크",
                bankColor = Color(0xFFFFEB3B).toArgb(),
            ),
        )
    AndroidpaymentsTheme {
        MultipleCardsScreen(
            cards = cards,
        )
    }
}

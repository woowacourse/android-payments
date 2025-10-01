package woowacourse.payments.ui.card.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.card.component.NewCard
import woowacourse.payments.ui.card.component.PaymentCard
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun OneCardScreen(
    card: CardUiModel,
    onAddNewCardClick: () -> Unit,
    onEditCardClick: (CardUiModel) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = 36.dp))
        PaymentCard(
            cardNumber = card.maskedNumber,
            expiredDate = card.formattedExpirationDate,
            ownerName = card.cardHolderName,
            bankName = card.bankName,
            backgroundColor = Color(card.bankColor),
            onClick = { onEditCardClick(card) },
        )
        Spacer(modifier = Modifier.padding(top = 36.dp))
        NewCard(onClick = onAddNewCardClick)
    }
}

@Preview
@Composable
fun OneCardScreenPreview() {
    val card =
        CardUiModel(
            id = 1,
            number = "1234567812345678",
            maskedNumber = "1234 - 5678 - **** - ****",
            expirationDate = "1124",
            formattedExpirationDate = "11/24",
            cardHolderName = "TAMA ONE",
            bankName = "국민카드",
            bankColor = 0xFFFBC02D,
            password = "1243"
        )

    AndroidpaymentsTheme {
        OneCardScreen(
            card = card,
            onAddNewCardClick = {},
            onEditCardClick = {},
        )
    }
}

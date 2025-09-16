package woowacourse.payments.ui.card.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = 36.dp))
        PaymentCard(
            cardNumber = card.number,
            expiredDate = card.expirationDate,
            ownerName = card.cardHolderName,
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
            number = "1234567890123456",
            expirationDate = "11/24",
            cardHolderName = "TAMA SEO",
        )

    AndroidpaymentsTheme {
        OneCardScreen(
            card = card,
            onAddNewCardClick = {},
        )
    }
}

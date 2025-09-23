package woowacourse.payments.ui.cards

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.component.PaymentCardsContent
import woowacourse.payments.ui.component.PaymentCardsTopBar
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentCardsScreen(
    paymentCards: List<PaymentCardUiModel>,
    onAddCard: () -> Unit,
    onEditCard: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            PaymentCardsTopBar(
                paymentCards.size >= 2,
                Modifier,
                onAddClick = onAddCard,
            )
        },
    ) { innerPadding ->
        PaymentCardsContent(
            modifier =
                Modifier
                    .padding(innerPadding),
            paymentCards = paymentCards,
            onAddCard = onAddCard,
            onEditCard = onEditCard,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardsPreview() {
    AndroidpaymentsTheme {
        PaymentCardsScreen(emptyList(), {}, {})
    }
}

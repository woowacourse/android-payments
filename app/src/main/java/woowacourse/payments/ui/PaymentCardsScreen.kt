package woowacourse.payments.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.component.PaymentCardsContent
import woowacourse.payments.ui.component.PaymentCardsTopBar
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentCardsScreen(
    registerOnCardAdded: ((PaymentCardUiModel) -> Unit) -> Unit,
    onAddCard: () -> Unit,
) {
    var paymentCards by rememberSaveable { mutableStateOf(listOf<PaymentCardUiModel>()) }

    LaunchedEffect(Unit) {
        registerOnCardAdded { newCard ->
            paymentCards = paymentCards + newCard
        }
    }

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
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardsPreview() {
    AndroidpaymentsTheme {
        PaymentCardsScreen({}, {})
    }
}

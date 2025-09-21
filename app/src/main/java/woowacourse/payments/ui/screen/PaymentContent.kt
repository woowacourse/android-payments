package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Column
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
fun PaymentContent(
    cards: List<CardUiModel>,
    showTopAdd: Boolean,
    canAddMore: Boolean,
    onAddCardClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PaymentTopBar(
                modifier = Modifier.fillMaxWidth(),
                onAddClick = if (showTopAdd) onAddCardClick else null,
            )
        },
    ) { innerPadding ->
        PaymentCards(
            cards = cards,
            canAddMore = canAddMore,
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
        PaymentContent(
            cards = emptyList(),
            showTopAdd = false,
            canAddMore = true,
            onAddCardClick = {},
        )
    }
}

@Preview(name = "카드가 한개 존재하는 경우")
@Composable
private fun PaymentScreenOneCardPreview() {
    AndroidpaymentsTheme {
        val sampleCard =
            CardUiModel(
                cardCompany = CardCompanyType.BC.toUiModel(),
                cardNumber = "1111 - 2222 - **** - ****",
                expirationDate = "04 / 21",
                userName = "GAHYUNKIM",
                password = "1234",
            )
        PaymentContent(
            cards = listOf(sampleCard),
            showTopAdd = false,
            canAddMore = true,
            onAddCardClick = {},
        )
    }
}

@Preview(name = "카드가 3개 이상 존재하는 경우")
@Composable
private fun PaymentScreenThreeCardsPreview() {
    val sampleCard =
        CardUiModel(
            cardCompany = CardCompanyType.BC.toUiModel(),
            cardNumber = "1111 - 2222 - **** - ****",
            expirationDate = "04 / 21",
            userName = "JOY",
            password = "1234",
        )
    AndroidpaymentsTheme {
        val cards =
            listOf(
                sampleCard,
                sampleCard,
                sampleCard,
            )
        PaymentContent(
            cards = cards,
            showTopAdd = true,
            canAddMore = false,
            onAddCardClick = {},
        )
    }
}

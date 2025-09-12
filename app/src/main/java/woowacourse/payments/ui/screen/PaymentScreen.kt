package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
                onAddClick = onAddCardClick.takeIf { cards.size >= 3 },
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

@Preview(showBackground = true)
@Composable
private fun PaymentScreenPreview() {
    AndroidpaymentsTheme {
        Column {
            Box(Modifier.height(300.dp)) {
                PaymentScreen(cards = emptyList(), onAddCardClick = {})
            }
            Box(Modifier.height(300.dp)) {
                PaymentScreen(
                    cards =
                        listOf(
                            CardUiModel("1111222233334444", "0421", "CREW", "1234"),
                        ),
                    onAddCardClick = {},
                )
            }
            Box(Modifier.height(400.dp)) {
                PaymentScreen(
                    cards =
                        listOf(
                            CardUiModel("1111222233334444", "0421", "CREW", "1234"),
                            CardUiModel("5555666677778888", "0522", "GAHYUN", "5678"),
                            CardUiModel("9999000011112222", "0623", "ANDY", "9012"),
                        ),
                    onAddCardClick = {},
                )
            }
        }
    }
}

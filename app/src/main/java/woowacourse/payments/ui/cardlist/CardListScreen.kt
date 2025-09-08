package woowacourse.payments.ui.cardlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.components.PaymentCard

@Composable
fun CardListScreen(
    cards: List<Card> = emptyList(),
    onAddClick: () -> Unit = {},
) {
    Scaffold(
        topBar = { CardListTopBar(onAddClick = onAddClick) },
    ) { innerPadding: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding() + 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp),
        ) {
            PaymentCard()
        }
    }
}

@Preview
@Composable
private fun CardListScreenPreview() {
    CardListScreen()
}

package woowacourse.payments.ui.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.debug.fixture.paymentCardUiModelSamples
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun MultiCardsSection(
    cards: List<CardUiModel>,
    onUpdateClick: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = modifier,
    ) {
        items(cards) { card ->
            Card(card, onUpdateClick, Modifier)
        }
    }
}

@Preview
@Composable
fun MultiCardsSectionPreview() {
    MultiCardsSection(
        paymentCardUiModelSamples, {}
    )
}

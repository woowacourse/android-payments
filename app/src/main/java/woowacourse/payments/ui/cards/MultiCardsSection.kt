package woowacourse.payments.ui.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.cards.core.mapper.asColor
import woowacourse.payments.ui.debug.fixture.cardUiModelSamples
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
            Card(card, modifier.clickable { onUpdateClick(card) }) {
                CardContent(
                    card,
                    Modifier.padding(15.dp),
                )
            }
        }
    }
}

@Preview
@Composable
fun MultiCardsSectionPreview() {
    MultiCardsSection(
        cardUiModelSamples, {}
    )
}

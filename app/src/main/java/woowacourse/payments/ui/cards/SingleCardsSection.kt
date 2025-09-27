package woowacourse.payments.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.cards.core.mapper.asColor
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.debug.fixture.cardUiModelSample

@Composable
fun SingleCardsSection(
    card: CardUiModel,
    onAddClick: () -> Unit,
    onUpdateClick: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = modifier,
    ) {
        Spacer(Modifier.height(12.dp))
        Card(
            card,
            Modifier
                .shadow(8.dp)
                .width(width = 208.dp)
                .clickable { onUpdateClick(card) },
        ) {
            CardContent(
                card,
                Modifier
                    .padding(15.dp),
            )
        }
        NewCard(onAddClick)
    }
}

@Preview(showBackground = true)
@Composable
fun SingleCardsSectionPreview() {
    SingleCardsSection(cardUiModelSample, {}, {})
}

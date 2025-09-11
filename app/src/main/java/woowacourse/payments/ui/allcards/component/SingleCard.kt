package woowacourse.payments.ui.allcards.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.component.Card
import woowacourse.payments.ui.uimodel.CardInfoUiState

@Composable
fun SingleCard(
    cards: List<CardInfoUiState>,
    onPlusCardClick: () -> Unit = {},
) {
    Spacer(modifier = Modifier.height(12.dp))
    Card(cardInfoUiState = cards.first())
    Spacer(modifier = Modifier.height(36.dp))
    PlusCard(
        onClick = onPlusCardClick,
    )
}

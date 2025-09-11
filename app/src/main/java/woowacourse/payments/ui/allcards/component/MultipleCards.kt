package woowacourse.payments.ui.allcards.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.component.Card
import woowacourse.payments.ui.uimodel.CardInfoUiState

@Composable
fun MultipleCards(cards: List<CardInfoUiState>) {
    Spacer(modifier = Modifier.height(12.dp))
    cards.forEach { cardInfoUiState ->
        Card(cardInfoUiState = cardInfoUiState)
        Spacer(modifier = Modifier.height(36.dp))
    }
}

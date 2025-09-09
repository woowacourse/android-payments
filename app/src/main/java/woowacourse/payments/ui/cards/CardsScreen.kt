package woowacourse.payments.ui.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.cards.model.CardsUiState

@Composable
fun CardsScreen(
    cardsStateHolder: CardsStateHolder,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = cardsStateHolder.cardsUiState
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        when (uiState) {
            CardsUiState.None -> NonCardsSection(onAddClick)
            is CardsUiState.Single -> SingleCardsSection(uiState.card)
            is CardsUiState.Multiple -> MultiCardsSection(uiState.cards)
        }
    }
}
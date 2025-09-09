package woowacourse.payments.ui.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CardsScreen(
    cardScreenState: CardScreenState,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        when (cardScreenState) {
            CardScreenState.None -> NonCardsSection(onAddClick)
            is CardScreenState.Single -> SingleCardsSection(cardScreenState.card)
            is CardScreenState.Multiple -> MultiCardsSection(cardScreenState.cards)
        }
    }
}
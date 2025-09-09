package woowacourse.payments.ui.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.cards.model.CardsUiState
import woowacourse.payments.ui.model.paymentCardUiModelSample
import woowacourse.payments.ui.model.paymentCardUiModelSamples

@Composable
fun CardsScreen(
    cardsUiState: CardsUiState,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        when (cardsUiState) {
            CardsUiState.None -> NonCardsSection(onAddClick)
            is CardsUiState.Single -> SingleCardsSection(onAddClick, cardsUiState.card)
            is CardsUiState.Multiple -> MultiCardsSection(cardsUiState.cards)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoneCardsScreenPreview() {
    CardsScreen(CardsUiState.None, {})
}

@Preview(showBackground = true)
@Composable
fun SingleCardsScreenPreview() {
    CardsScreen(CardsUiState.Single(paymentCardUiModelSample), {})
}

@Preview(showBackground = true)
@Composable
fun MultipleCardsScreenPreview() {
    CardsScreen(CardsUiState.Multiple(paymentCardUiModelSamples), {})
}
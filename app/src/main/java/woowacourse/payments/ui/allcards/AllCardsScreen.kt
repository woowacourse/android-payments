package woowacourse.payments.ui.allcards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.allcards.component.AllCardsTopbar
import woowacourse.payments.ui.allcards.component.EmptyCard
import woowacourse.payments.ui.allcards.component.MultipleCards
import woowacourse.payments.ui.allcards.component.SingleCard
import woowacourse.payments.ui.allcards.model.AllCardsUiState
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.uimodel.CardInfoUiState

@Composable
fun AllCardsScreen(
    allCards: AllCardsUiState,
    modifier: Modifier = Modifier,
    onPlusCardClick: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (allCards.viewType) {
            AllCardsUiState.ViewType.EMPTY -> EmptyCard(onPlusCardClick)
            AllCardsUiState.ViewType.SINGLE -> SingleCard(allCards.cards, onPlusCardClick)
            AllCardsUiState.ViewType.MULTIPLE -> MultipleCards(allCards.cards)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AllCardsScreenPreview() {
    AndroidpaymentsTheme {
        val cards = rememberSaveable { mutableStateListOf<CardInfoUiState>() }
        Scaffold(
            topBar = {
                AllCardsTopbar(
                    allCards = AllCardsUiState(cards),
                )
            },
        ) {
            AllCardsScreen(
                allCards = AllCardsUiState(cards),
                modifier = Modifier.padding(it),
            )
        }
    }
}

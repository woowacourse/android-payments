package woowacourse.payments.view.cards.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.view.cards.CardsStateHolder
import woowacourse.payments.view.cards.CardsUiEvent
import woowacourse.payments.view.cards.CardsUiState
import woowacourse.payments.view.ui.model.BankTypeUiModel
import woowacourse.payments.view.ui.model.CardUiModel
import woowacourse.payments.view.ui.theme.AndroidpaymentsTheme

@Composable
fun CardsScreen(
    stateHolder: CardsStateHolder,
    onUiEvent: (CardsUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state: CardsUiState = stateHolder.uiState

    Scaffold(
        modifier = modifier,
        topBar = {
            CardsTopAppBar(
                addCardAction =
                    if (state.cards.size > 1) {
                        { onUiEvent(CardsUiEvent.NavigateToCardAddition) }
                    } else {
                        null
                    },
            )
        },
    ) { innerPadding: PaddingValues ->
        CardsContent(
            state = state,
            addCard = { onUiEvent(CardsUiEvent.NavigateToCardAddition) },
            editCard = { card: CardUiModel -> onUiEvent(CardsUiEvent.NavigateToCardEditing(card)) },
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        )
    }
}

@Composable
private fun CardsContent(
    state: CardsUiState,
    addCard: () -> Unit,
    editCard: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
) {
    Column(
        modifier = modifier,
    ) {
        when (state.cards.size) {
            0 ->
                NoCardContent(
                    addCard = addCard,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                )

            1 ->
                OneCardContent(
                    card = state.cards.first(),
                    addCard = addCard,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                    onClickCard = editCard,
                )

            else ->
                MultipleCardContent(
                    cards = state.cards,
                    onClickCard = editCard,
                    modifier = Modifier.fillMaxSize(),
                )
        }
    }
}

@Preview
@Composable
private fun CardsScreenPreview(
    @PreviewParameter(CardsScreenPreviewParameterProvider::class) cards: List<CardUiModel>,
) {
    AndroidpaymentsTheme {
        CardsScreen(
            stateHolder = CardsStateHolder(CardsUiState(cards)),
            onUiEvent = {},
        )
    }
}

private class CardsScreenPreviewParameterProvider : PreviewParameterProvider<List<CardUiModel>> {
    override val values: Sequence<List<CardUiModel>> =
        sequenceOf(
            emptyList(),
            listOf(
                CardUiModel(
                    number = "1234".repeat(4),
                    holder = "CREW",
                    expiredDate = "0421",
                    bankType = BankTypeUiModel.BC,
                ),
            ),
            listOf(
                CardUiModel(
                    number = "1234".repeat(4),
                    holder = "CREW",
                    expiredDate = "0421",
                    bankType = BankTypeUiModel.KB,
                ),
                CardUiModel(
                    number = "1234".repeat(4),
                    holder = "CREW",
                    expiredDate = "0421",
                    bankType = BankTypeUiModel.HANA,
                ),
                CardUiModel(
                    number = "1234".repeat(4),
                    holder = "CREW",
                    expiredDate = "0421",
                    bankType = BankTypeUiModel.KAKAO,
                ),
            ),
        )
}

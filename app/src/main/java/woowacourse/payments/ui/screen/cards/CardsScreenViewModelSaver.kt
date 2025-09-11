package woowacourse.payments.ui.screen.cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberCardsScreenViewModel(): CardsScreenViewModel {
    val saver =
        Saver<CardsScreenViewModel, CardsUiState>(
            save = { viewModel -> viewModel.uiState.value ?: CardsUiState.EMPTY },
            restore = { uiState -> CardsScreenViewModel(uiState) },
        )

    return rememberSaveable(saver = saver) {
        CardsScreenViewModel()
    }
}

package woowacourse.payments.ui.screen.cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable

class CardsScreenViewModelSaver : Saver<CardsViewModel, CardsUiState> {
    override fun SaverScope.save(value: CardsViewModel): CardsUiState = value.uiState

    override fun restore(value: CardsUiState): CardsViewModel = CardsViewModel(initialUiState = value)
}

@Composable
fun rememberCardsScreenViewModel(): CardsViewModel =
    rememberSaveable(saver = CardsScreenViewModelSaver()) {
        CardsViewModel()
    }

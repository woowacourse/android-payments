package woowacourse.payments.ui.screen.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable

class CardRegistrationScreenViewModelSaver : Saver<CardRegistrationScreenViewModel, CardRegistrationScreenUiState> {
    override fun SaverScope.save(value: CardRegistrationScreenViewModel): CardRegistrationScreenUiState = value.uiState

    override fun restore(value: CardRegistrationScreenUiState): CardRegistrationScreenViewModel =
        CardRegistrationScreenViewModel(initialUiState = value)
}

@Composable
fun rememberCardRegistrationScreenViewModel(): CardRegistrationScreenViewModel =
    rememberSaveable(saver = CardRegistrationScreenViewModelSaver()) {
        CardRegistrationScreenViewModel()
    }

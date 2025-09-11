package woowacourse.payments.ui.screen.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberCardRegistrationScreenViewModel(): CardRegistrationScreenViewModel {
    val saver =
        Saver<CardRegistrationScreenViewModel, CardRegistrationScreenUiState>(
            save = { viewModel -> viewModel.uiState.value },
            restore = { uiState -> CardRegistrationScreenViewModel(uiState) },
        )

    return rememberSaveable(saver = saver) {
        CardRegistrationScreenViewModel()
    }
}

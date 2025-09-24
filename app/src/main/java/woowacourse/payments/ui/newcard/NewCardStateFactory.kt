package woowacourse.payments.ui.newcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberNewCardState(initialUiState: NewCardUiState): NewCardStateHolder =
    rememberSaveable(saver = NewCardStateHolderSaver) {
        NewCardStateHolder(initialUiState)
    }

private val NewCardStateHolderSaver: Saver<NewCardStateHolder, NewCardUiState> =
    Saver(
        save = { stateHolder: NewCardStateHolder -> stateHolder.uiState },
        restore = { uiState: NewCardUiState -> NewCardStateHolder(uiState) },
    )

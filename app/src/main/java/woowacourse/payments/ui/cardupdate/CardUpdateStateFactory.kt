package woowacourse.payments.ui.cardupdate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberNewCardState(initialUiState: CardUpdateUiState): CardUpdateStateHolder =
    rememberSaveable(saver = CardUpdateStateHolderSaver) {
        CardUpdateStateHolder(initialUiState)
    }

private val CardUpdateStateHolderSaver: Saver<CardUpdateStateHolder, CardUpdateUiState> =
    Saver(
        save = { stateHolder: CardUpdateStateHolder -> stateHolder.uiState },
        restore = { uiState: CardUpdateUiState -> CardUpdateStateHolder(uiState) },
    )

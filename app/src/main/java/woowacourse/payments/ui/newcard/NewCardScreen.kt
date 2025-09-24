package woowacourse.payments.ui.newcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.create.CreateCardScreen
import woowacourse.payments.ui.newcard.model.NewCardMode
import woowacourse.payments.ui.newcard.update.UpdateCardScreen

@Composable
fun NewCardScreen(
    onSaveClick: (CardUiModel) -> Unit,
    onBackClick: () -> Unit,
    mode: NewCardMode,
    modifier: Modifier = Modifier,
) {
    val newCardStateHolder =
        rememberSaveable(saver = NewCardStateHolderSaver()) { NewCardStateHolder() }
    when (mode) {
        NewCardMode.Create -> {
            CreateCardScreen(newCardStateHolder, onSaveClick, onBackClick, modifier)
        }

        is NewCardMode.Update -> {
            LaunchedEffect(mode.cardUiModel) {
                newCardStateHolder.updateCardInfo(mode.cardUiModel)
            }
            UpdateCardScreen(
                newCardStateHolder,
                mode.cardUiModel,
                onSaveClick,
                onBackClick,
                modifier
            )
        }
    }
}
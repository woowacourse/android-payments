package woowacourse.payments.ui.newcard

import androidx.compose.runtime.Composable
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
    when (mode) {
        NewCardMode.Create -> {
            CreateCardScreen(onSaveClick, onBackClick, modifier)
        }

        is NewCardMode.Update -> {
            UpdateCardScreen(
                mode.cardUiModel,
                onSaveClick,
                onBackClick,
                modifier
            )
        }
    }
}
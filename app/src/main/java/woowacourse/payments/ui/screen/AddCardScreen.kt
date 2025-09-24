package woowacourse.payments.ui.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.R
import woowacourse.payments.domain.model.Card
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun AddCardScreen(
    onBackPressed: () -> Unit,
    onAddCard: (Card) -> Unit,
    initialShowSheet: Boolean = true,
    initialCard: CardUiModel? = null,
) {
    val context = LocalContext.current
    val stateHolder =
        remember {
            AddCardStateHolder(
                initialShowSheet = initialShowSheet,
                initial = initialCard,
            )
        }

    LaunchedEffect(stateHolder.uiEvent) {
        when (stateHolder.uiEvent) {
            AddCardUiEvent.ShowNoChangesToast -> {
                Toast
                    .makeText(context, R.string.add_card_toast_no_change, Toast.LENGTH_SHORT)
                    .show()
                stateHolder.consumeEvent()
            }

            AddCardUiEvent.ShowCardAddedToast -> {
                Toast
                    .makeText(context, R.string.payment_toast_card_added, Toast.LENGTH_SHORT)
                    .show()
                stateHolder.consumeEvent()
            }

            null -> Unit
        }
    }

    AddCardContent(
        uiState = stateHolder.uiState,
        cardPreview = stateHolder.cardPreview,
        onBackPressed = onBackPressed,
        onNumberChange = stateHolder::onNumberChange,
        onExpirationChange = stateHolder::onExpirationChange,
        onUserNameChange = stateHolder::onUserNameChange,
        onPasswordChange = stateHolder::onPasswordChange,
        onSelectCompany = stateHolder::onSelectCompany,
        onDismissSheet = stateHolder::onDismissSheet,
        onSaveClick = { stateHolder.onSaveClick(onAddCard) },
    )
}

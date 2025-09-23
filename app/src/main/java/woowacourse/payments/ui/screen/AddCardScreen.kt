package woowacourse.payments.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import woowacourse.payments.domain.model.Card
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun AddCardScreen(
    onBackPressed: () -> Unit,
    onAddCard: (Card) -> Unit,
    initialShowSheet: Boolean = true,
    initialCard: CardUiModel? = null,
) {
    val stateHolder =
        remember {
            AddCardStateHolder(
                initialShowSheet = initialShowSheet,
                initial = initialCard,
            )
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

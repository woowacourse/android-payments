package woowacourse.payments.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import woowacourse.payments.domain.model.Card

@Composable
fun AddCardScreenHost(
    onBackPressed: () -> Unit,
    onAddCard: (Card) -> Unit,
    initialShowSheet: Boolean = true,
) {
    val stateHolder = remember { AddCardScreenStateHolder(initialShowSheet = initialShowSheet) }

    AddCardScreen(
        uiState = stateHolder.uiState,
        cardForPreview = stateHolder.cardForPreview,
        onBackPressed = onBackPressed,
        onAddCard = onAddCard,
        onNumberChange = stateHolder::onNumberChange,
        onExpirationChange = stateHolder::onExpirationChange,
        onUserNameChange = stateHolder::onUserNameChange,
        onPasswordChange = stateHolder::onPasswordChange,
        onDismissSheet = stateHolder::onDismissSheet,
        onSelectCardCompany = stateHolder::onSelectCardCompany,
        onSaveClick = { stateHolder.onSaveClick(onAddCard) },
    )
}

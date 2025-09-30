package woowacourse.payments.ui.screen

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.model.Card
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.domain.model.CardNumber
import woowacourse.payments.domain.model.ExpirationDate
import woowacourse.payments.domain.model.Password
import woowacourse.payments.domain.model.UserName
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel

class AddCardStateHolder(
    initialShowSheet: Boolean,
    private val initial: CardUiModel? = null,
) {
    var uiState by mutableStateOf(
        AddCardUiState(showCompanySheet = initialShowSheet).let { cardUiState ->
            initial?.let { cardUiModel ->
                cardUiState.copy(
                    number = cardUiModel.cardNumberRaw,
                    expiration = cardUiModel.expirationDateRaw,
                    userName = cardUiModel.userName ?: "",
                    password = cardUiModel.password,
                    selectedCompany = cardUiModel.cardCompany.type,
                )
            } ?: cardUiState
        },
    )
        private set

    var uiEvent by mutableStateOf<AddCardUiEvent?>(null)
        private set

    fun consumeEvent() {
        uiEvent = null
    }

    val cardPreview by derivedStateOf {
        CardUiModel.EMPTY.copy(
            id = initial?.id ?: CardUiModel.UNASSIGNED_ID,
            cardCompany = uiState.selectedCompany.toUiModel(),
            cardNumberRaw = uiState.number,
            expirationDateRaw = uiState.expiration,
            userName = uiState.userName,
            password = uiState.password,
        )
    }

    private val hasChanges by derivedStateOf {
        val init = initial ?: return@derivedStateOf true
        uiState.number != init.cardNumberRaw ||
            uiState.expiration != init.expirationDateRaw ||
            uiState.userName != (init.userName ?: "") ||
            uiState.password != init.password ||
            uiState.selectedCompany != init.cardCompany.type
    }

    private val isValid by derivedStateOf {
        uiState.selectedCompany != CardCompanyType.NOT_SELECTED &&
            uiState.numberError == null &&
            uiState.expirationError == null &&
            uiState.userNameError == null &&
            uiState.passwordError == null
    }

    val isSaveEnabled by derivedStateOf { isValid && (initial != null || hasChanges) }

    fun onNumberChange(value: String) {
        uiState =
            uiState.copy(
                number = value,
                numberError = CardNumber.validationErrorType(value),
            )
    }

    fun onExpirationChange(value: String) {
        uiState =
            uiState.copy(
                expiration = value,
                expirationError = ExpirationDate.validationErrorType(value),
            )
    }

    fun onUserNameChange(value: String) {
        uiState =
            uiState.copy(
                userName = value,
                userNameError = UserName.validationErrorType(value),
            )
    }

    fun onPasswordChange(value: String) {
        uiState =
            uiState.copy(
                password = value,
                passwordError = Password.validationErrorType(value),
            )
    }

    fun onSelectCompany(cardCompany: CardCompanyType) {
        uiState =
            uiState.copy(
                selectedCompany = cardCompany,
                showCompanySheet = false,
            )
    }

    fun onDismissSheet() {
        uiState = uiState.copy(showCompanySheet = false)
    }

    fun onSaveClick(onAddCard: (Card) -> Unit) {
        uiState =
            uiState.copy(
                numberError = CardNumber.validationErrorType(uiState.number),
                expirationError = ExpirationDate.validationErrorType(uiState.expiration),
                userNameError = UserName.validationErrorType(uiState.userName),
                passwordError = Password.validationErrorType(uiState.password),
            )

        if (uiState.selectedCompany == CardCompanyType.NOT_SELECTED) {
            uiState = uiState.copy(showCompanySheet = true)
            return
        }

        if (initial != null && !hasChanges) {
            uiEvent = AddCardUiEvent.ShowNoChangesToast
            return
        }

        if (!isSaveEnabled) return

        try {
            val card =
                Card(
                    id = initial?.id ?: CardUiModel.UNASSIGNED_ID,
                    cardNumber = CardNumber.create(uiState.number),
                    expirationDate = ExpirationDate.createFromRaw(uiState.expiration),
                    userName = UserName.create(uiState.userName),
                    password = Password.create(uiState.password),
                    type = uiState.selectedCompany,
                )
            onAddCard(card)
            uiEvent = AddCardUiEvent.ShowCardAddedToast
        } catch (_: IllegalArgumentException) {
            uiState =
                uiState.copy(
                    numberError = CardNumber.validationErrorType(uiState.number),
                    expirationError = ExpirationDate.validationErrorType(uiState.expiration),
                    userNameError = UserName.validationErrorType(uiState.userName),
                    passwordError = Password.validationErrorType(uiState.password),
                )
        }
    }
}

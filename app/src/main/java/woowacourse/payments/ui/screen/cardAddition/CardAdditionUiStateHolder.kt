package woowacourse.payments.ui.screen.cardAddition

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.IssuingBank

class CardAdditionUiStateHolder(
    initialState: CardAdditionUiState = CardAdditionUiState.EMPTY_CARD,
) {
    var uiState by mutableStateOf(initialState)
        private set
    var hasShownSheet by mutableStateOf(false)
        private set
    val cardUiModel by derivedStateOf { uiState.toUiModel() }
    val isCompletable by derivedStateOf { uiState.isValidCard }

    fun updateCardState(
        newCardNumber: String? = null,
        newExpiredDate: String? = null,
        newOwnerName: String? = null,
        newPassword: String? = null,
        newIssuingBank: IssuingBank? = null,
    ) {
        uiState =
            uiState.update(
                newCardNumber = newCardNumber,
                newExpiredDate = newExpiredDate,
                newOwnerName = newOwnerName,
                newPassword = newPassword,
                newIssuingBank = newIssuingBank,
            )
    }

    fun updateSheetVisible() {
        hasShownSheet = hasShownSheet.not()
    }

    private fun CardAdditionUiState.toUiModel(): CardUiModel =
        CardUiModel(
            number = cardNumber.value,
            expiredDate = expiredDate.value,
            ownerName = ownerName,
            issuingBank = issuingBank,
        )

    companion object {
        val Saver: Saver<CardAdditionUiStateHolder, *> =
            Saver(
                save = { holder ->
                    listOf(
                        holder.uiState.cardNumber.value,
                        holder.uiState.expiredDate.value,
                        holder.uiState.ownerName,
                        holder.uiState.password.value,
                        holder.uiState.issuingBank,
                    )
                },
                restore = { saver ->
                    val (number, date, owner, password, issuingBank) = saver
                    CardAdditionUiStateHolder(
                        CardAdditionUiState(
                            cardNumber = CardNumber(number as String),
                            expiredDate = ExpiredDate(date as String),
                            ownerName = owner as String,
                            password = CardPassword(password as String),
                            issuingBank = issuingBank as IssuingBank,
                        ),
                    )
                },
            )
    }
}

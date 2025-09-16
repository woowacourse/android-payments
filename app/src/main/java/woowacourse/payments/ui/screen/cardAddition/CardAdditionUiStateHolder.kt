package woowacourse.payments.ui.screen.cardAddition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.ui.model.IssuingBank

class CardAdditionUiStateHolder(
    initialState: CardAdditionUiState = CardAdditionUiState.EMPTY_CARD,
) {
    var uiState by mutableStateOf(initialState)
        private set
    var hasShownSheet by mutableStateOf(false)
        private set

    fun updateCardNumber(newCardNumber: String) {
        uiState = uiState.update(newCardNumber = newCardNumber)
    }

    fun updateExpiredDate(newExpiredDate: String) {
        uiState = uiState.update(newExpiredDate = newExpiredDate)
    }

    fun updateCardOwnerName(newOwnerName: String) {
        uiState = uiState.update(newOwnerName = newOwnerName)
    }

    fun updatePassword(newPassword: String) {
        uiState = uiState.update(newPassword = newPassword)
    }

    fun updateIssuingBank(issuingBank: IssuingBank) {
        uiState = uiState.update(issuingBank = issuingBank)
    }

    fun updateSheetVisible() {
        hasShownSheet = hasShownSheet.not()
    }

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

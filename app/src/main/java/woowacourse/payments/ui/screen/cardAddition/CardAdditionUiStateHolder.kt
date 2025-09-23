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
    private val originalCard: CardUiModel? = null,
) {
    var uiState by mutableStateOf(originalCard?.toState() ?: CardAdditionUiState.EMPTY_CARD)
        private set
    var hasShownSheet by mutableStateOf(false)
        private set
    val card by derivedStateOf { uiState.toUiModel() }
    val isCompletable by derivedStateOf { uiState.isValidCard && (originalCard == null || originalCard != card) }

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
            password = password.value,
            issuingBank = issuingBank,
        )

    private fun CardUiModel.toState(): CardAdditionUiState =
        CardAdditionUiState(
            cardNumber = CardNumber(number),
            expiredDate = ExpiredDate(expiredDate),
            ownerName = ownerName,
            password = CardPassword(password),
            issuingBank = issuingBank,
        )

    companion object {
        val Saver: Saver<CardAdditionUiStateHolder, *> =
            Saver(
                save = { holder ->
                    listOf(
                        holder.originalCard,
                        holder.uiState.cardNumber.value,
                        holder.uiState.expiredDate.value,
                        holder.uiState.ownerName,
                        holder.uiState.password.value,
                        holder.uiState.issuingBank,
                    )
                },
                restore = { saved ->
                    val originalCard = saved[0] as CardUiModel?
                    val number = saved[1] as String
                    val date = saved[2] as String
                    val owner = saved[3] as String
                    val password = saved[4] as String
                    val issuingBank = saved[5] as IssuingBank

                    val restoredUiState =
                        CardAdditionUiState(
                            cardNumber = CardNumber(number),
                            expiredDate = ExpiredDate(date),
                            ownerName = owner,
                            password = CardPassword(password),
                            issuingBank = issuingBank,
                        )

                    CardAdditionUiStateHolder(originalCard).apply {
                        uiState = restoredUiState
                    }
                },
            )
    }
}

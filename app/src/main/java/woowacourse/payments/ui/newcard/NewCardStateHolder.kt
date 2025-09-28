package woowacourse.payments.ui.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.model.Bank
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.model.NewCardStateHolderSnapshot

class NewCardStateHolder(
    val id: Int = UNINITIALIZED_ID,
) {
    private var _cardNumber by mutableStateOf("")
    val cardNumber get() = _cardNumber

    private var _cardHolder by mutableStateOf("")
    val cardHolder get() = _cardHolder

    val expirationDateUiState = ExpirationDateUiState("")

    private var _password by mutableStateOf("")
    val password get() = _password

    private var _bank by mutableStateOf(Bank())
    val bank get() = _bank

    fun updateCardNumber(newCardNumber: String) {
        _cardNumber = newCardNumber
    }

    fun updateCardHolder(newCardHolder: String) {
        _cardHolder = newCardHolder
    }

    fun updateBank(newBank: Bank) {
        _bank = newBank
    }

    fun updatePassword(newPassword: String) {
        _password = newPassword
    }

    fun isModified(initialCard: PaymentCardUiModel?): Boolean {
        if (initialCard == null) return true

        return cardNumber != initialCard.cardNumber.value ||
            cardHolder != initialCard.cardHolder.value ||
            expirationDateUiState.expirationDate.value != initialCard.expirationDate.value ||
            bank.type != initialCard.bankType
    }

    companion object {
        const val UNINITIALIZED_ID = 0

        val NewCardStateHolderSaver: Saver<NewCardStateHolder, Any> =
            Saver(
                save = { holder ->
                    NewCardStateHolderSnapshot(
                        id = holder.id,
                        cardNumber = holder.cardNumber,
                        cardHolder = holder.cardHolder,
                        rawExpirationDate = holder.expirationDateUiState.rawExpirationDate,
                        password = holder.password,
                        bank = holder.bank,
                    )
                },
                restore = { restored ->
                    val snapshot = restored as NewCardStateHolderSnapshot
                    return@Saver NewCardStateHolder(snapshot.id).apply {
                        updateCardNumber(snapshot.cardNumber)
                        updateCardHolder(snapshot.cardHolder)
                        expirationDateUiState.onValueChanged(snapshot.rawExpirationDate)
                        updatePassword(snapshot.password)
                        updateBank(snapshot.bank)
                    }
                },
            )
    }
}

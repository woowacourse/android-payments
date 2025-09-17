package woowacourse.payments.ui.newcard.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.newcard.uiModel.BankTypeUiModel
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CardStateHolder {
    var card by mutableStateOf<Card?>(null)
        private set

    var bankTypeUiModel by mutableStateOf(BankTypeUiModel.NOT_SELECTED)
        private set

    var number by mutableStateOf("")
        private set
    var expirationDate by mutableStateOf("")
        private set
    var ownerName by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var cardErrorMessage by mutableStateOf<String?>(null)
    var numberErrorMessage by mutableStateOf<String?>(null)
        private set
    var expirationDateErrorMessage by mutableStateOf<String?>(null)
        private set
    var ownerNameErrorMessage by mutableStateOf<String?>(null)
        private set
    var passwordErrorMessage by mutableStateOf<String?>(null)
        private set

    fun newCard(): Card? {
        bankTypeUiModel.let { bankType ->
            runCatching {
                Card.Companion.Card(
                    bankTypeUiModel = bankType,
                    number = number,
                    expirationDate = YearMonth.parse(
                        expirationDate,
                        DateTimeFormatter.ofPattern("MMyy")
                    ),
                    ownerName = ownerName,
                    password = password,
                )
            }.onSuccess { newCard ->
                card = newCard
                return card
            }.onFailure { e ->
                cardErrorMessage = e.message
            }
        }
        return null
    }

    fun changeBankType(newBank: BankTypeUiModel) {
        if (newBank != BankTypeUiModel.NOT_SELECTED) {
            bankTypeUiModel = newBank
        }
    }

    fun changeNumber(newNumber: String) {
        number = newNumber
        if (newNumber.isEmpty()) {
            numberErrorMessage = null; return
        }
        runCatching {
            CardNumber(
                value = newNumber.filter { it.isDigit() }.take(16)
            )
        }.onSuccess { numberErrorMessage = null }
            .onFailure { e -> numberErrorMessage = e.message }
    }

    fun changeExpirationDate(newExpirationDate: String) {
        expirationDate = newExpirationDate
        if (newExpirationDate.isEmpty()) {
            expirationDateErrorMessage = null; return
        }
        runCatching {
            val digits = newExpirationDate.filter { it.isDigit() }.take(4)
            ExpirationDate(
                value = YearMonth.parse(digits, DateTimeFormatter.ofPattern("MMyy"))
            )
        }.onSuccess { expirationDateErrorMessage = null }
            .onFailure { e -> expirationDateErrorMessage = e.message }
    }

    fun changeOwnerName(newOwnerName: String) {
        ownerName = newOwnerName

        if (newOwnerName.isEmpty()) {
            ownerNameErrorMessage = null
            return
        }

        runCatching {
            OwnerName(OwnerName(newOwnerName).maxName())
        }.onSuccess {
            ownerNameErrorMessage = null
        }.onFailure { e ->
            ownerNameErrorMessage = e.message
        }
    }

    fun changePassword(newPassword: String) {
        password = newPassword
        if (newPassword.isEmpty()) {
            passwordErrorMessage = null; return
        }
        runCatching {
            Password(newPassword.take(4))
        }.onSuccess { passwordErrorMessage = null }
            .onFailure { e -> passwordErrorMessage = e.message }
    }
}
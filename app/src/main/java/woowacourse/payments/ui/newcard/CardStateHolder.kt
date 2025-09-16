package woowacourse.payments.ui.newcard

import android.util.Log
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

    fun newCard() {
        bankTypeUiModel?.let { bankType ->
            runCatching {
                Card.Companion.Card(
                    bankTypeUiModel = bankType,
                    number = number,
                    expirationDate = expirationDate,
                    ownerName = ownerName,
                    password = password,
                )
            }.onSuccess { newCard ->
                card = newCard
            }.onFailure { e ->
                cardErrorMessage = e.message
            }
        }
    }

    fun changeBankType(newBank: BankTypeUiModel) {
        if (newBank != BankTypeUiModel.NOT_SELECTED) {
            bankTypeUiModel = newBank
            Log.d("test", "선택됨")
        }
    }

    fun changeNumber(newNumber: String) {
        if (number.isNotEmpty())
            runCatching {
                CardNumber(value = number.removeSurrounding(" - ").take(16))
            }.onSuccess {
                numberErrorMessage = null
            }.onFailure { e: Throwable ->
                numberErrorMessage = e.message
            }
    }

    fun changeExpirationDate(newExpirationDate: String) {
        if (expirationDate.isNotEmpty()) {
            runCatching {
                ExpirationDate(
                    value = YearMonth.parse(
                        expirationDate.removeSurrounding("/").take(4),
                        DateTimeFormatter.ofPattern("MMyy")
                    )
                )
            }.onSuccess {
                expirationDateErrorMessage = null
            }.onFailure { e: Throwable ->
                expirationDateErrorMessage = e.message
            }
        }
    }

    fun changeOwnerName(newOwnerName: String) {
        if (ownerName.isNotEmpty()) {
            runCatching {
                OwnerName(ownerName.take(30))
            }.onSuccess {
                ownerNameErrorMessage = null
            }.onFailure { e: Throwable ->
                ownerNameErrorMessage = e.message
            }
        }
    }

    fun changePassword(newPassword: String) {
        if (password.isNotEmpty()) {
            runCatching {
                Password(password.take(4))
            }.onSuccess {
                passwordErrorMessage = null
            }.onFailure { e: Throwable ->
                passwordErrorMessage = e.message
            }
        }
    }
}
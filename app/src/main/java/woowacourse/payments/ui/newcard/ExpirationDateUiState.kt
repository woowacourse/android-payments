package woowacourse.payments.ui.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.ExpirationDateUiModel

class ExpirationDateUiState(
    expirationDate: String,
) {
    var expirationDate by mutableStateOf(ExpirationDateUiModel(expirationDate))
        private set

    var isError: Boolean by mutableStateOf(this.expirationDate.isValidMonth())

    fun onValueChanged(value: String) {
        val newCardNumber = ExpirationDateUiModel(value)
        expirationDate = newCardNumber
        isError = newCardNumber.isValidMonth()
    }
}

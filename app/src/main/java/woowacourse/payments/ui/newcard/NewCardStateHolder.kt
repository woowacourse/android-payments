package woowacourse.payments.ui.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.model.Bank

class NewCardStateHolder {
    var cardNumber by mutableStateOf("")
    var cardHolder by mutableStateOf("")
    val expirationDateUiState by mutableStateOf(ExpirationDateUiState(""))
    var password by mutableStateOf("")
    var bank by mutableStateOf(Bank())
}

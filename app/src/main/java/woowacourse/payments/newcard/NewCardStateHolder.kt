package woowacourse.payments.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class NewCardStateHolder {
    var cardNumber: String by mutableStateOf("")
        private set

    var expiredDate: String by mutableStateOf("")
        private set

    var ownerName: String by mutableStateOf("")
        private set

    var password: String by mutableStateOf("")
        private set

    fun updateCardNumber(value: String) {
        cardNumber = value
    }

    fun updateExpiredDate(value: String) {
        expiredDate = value
    }

    fun updateOwnerName(value: String) {
        ownerName = value
    }

    fun updatePassword(value: String) {
        password = value
    }
}

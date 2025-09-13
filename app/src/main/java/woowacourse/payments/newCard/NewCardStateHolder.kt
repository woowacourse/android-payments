package woowacourse.payments.newCard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class NewCardStateHolder {
    var cardNumber: String by mutableStateOf("")
    var cardExpiry: String by mutableStateOf("")
    var cardName: String by mutableStateOf("")
    var cardPassword: String by mutableStateOf("")
}
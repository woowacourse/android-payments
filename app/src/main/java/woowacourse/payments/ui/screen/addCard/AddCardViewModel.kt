package woowacourse.payments.ui.screen.addCard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.domain.Expired
import woowacourse.payments.domain.Password

class AddCardViewModel {
    var cardNumber by mutableStateOf<CardNumber?>(null)
        private set
    var expired by mutableStateOf<Expired?>(null)
        private set
    var cardOwner by mutableStateOf<CardOwner?>(CardOwner(""))
        private set
    var password by mutableStateOf<Password?>(null)
        private set
    var showValidationError by mutableStateOf(false)
        private set

    fun onCardNumberChange(text: CardNumber?) {
        cardNumber = text
    }

    fun onExpiredChange(text: Expired?) {
        expired = text
    }

    fun onCardOwnerChange(text: CardOwner?) {
        cardOwner = text
    }

    fun onPasswordChange(text: Password?) {
        password = text
    }

    fun validateAll() {
        showValidationError = true
        val isValid =
            (cardNumber?.isValid == true) &&
                (expired?.isValid == true) &&
                (cardOwner?.isValid != false) &&
                (password?.isValid == true)
        if (isValid) showValidationError = false
    }
}

package woowacourse.payments.ui.addcard

import androidx.compose.foundation.text.input.TextFieldState

data class CardInfoUiState(
    val cardNumber: String = "",
    val expireDate: String = "",
    val ownerName: String = "",
    val password: String = ""
) {
    fun onValueChanged(
        cardNumber: String = this.cardNumber,
        expireDate: String = this.expireDate,
        ownerName: String = this.ownerName,
        password: String = this.password
    ): CardInfoUiState =
        copy(
            cardNumber
                .slice(0 until cardNumber.length.coerceAtMost(CARD_NUMBER_MAX_SIZE))
                .filter { it.isDigit() }
            ,
            expireDate
                .slice(0 until expireDate.length.coerceAtMost(EXPIRE_DATE_MAX_SIZE))
                .filter { it.isDigit() }
            ,
            ownerName.slice(0 until ownerName.length.coerceAtMost(OWNER_NAME_MAX_SIZE)),
            password
                .slice(0 until password.length.coerceAtMost(PASSWORD_MAX_SIZE))
                .filter { it.isDigit() }

        )

    companion object {
        const val OWNER_NAME_MAX_SIZE = 30
        private const val CARD_NUMBER_MAX_SIZE = 16
        private const val PASSWORD_MAX_SIZE = 4
        private const val EXPIRE_DATE_MAX_SIZE = 4
    }
}
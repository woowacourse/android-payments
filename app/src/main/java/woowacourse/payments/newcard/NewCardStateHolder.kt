package woowacourse.payments.newcard

class NewCardStateHolder {
    var cardNumber: String = ""
        private set

    var expiredDate: String = ""
        private set

    var ownerName: String = ""
        private set

    var password: String = ""
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

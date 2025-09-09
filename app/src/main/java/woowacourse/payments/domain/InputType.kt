package woowacourse.payments.domain

sealed class InputType(val maxLength: Int) {
    data object CardNumber : InputType(maxLength = 16)
    data object ExpiryDate : InputType(maxLength = 4)
    data object Password : InputType(maxLength = 4)
    data object CardholderName : InputType(maxLength = 30)
}

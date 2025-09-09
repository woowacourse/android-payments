package woowacourse.payments.ui.model

data class PaymentCardUiModel(
    val cardNumbers: String,
    val cardExpiry: String,
    val ownerName: String,
    val password: String
)
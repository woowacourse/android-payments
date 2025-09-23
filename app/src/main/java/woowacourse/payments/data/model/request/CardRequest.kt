package woowacourse.payments.data.model.request

sealed interface CardRequest {
    val numberDigits: String
    val expiry: String
    val holder: String
    val bankType: String
}

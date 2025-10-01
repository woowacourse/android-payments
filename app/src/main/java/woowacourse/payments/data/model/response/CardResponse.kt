package woowacourse.payments.data.model.response

data class CardResponse(
    val id: Long,
    val numberDigits: String,
    val expiry: String,
    val holder: String,
    val bankType: String,
)

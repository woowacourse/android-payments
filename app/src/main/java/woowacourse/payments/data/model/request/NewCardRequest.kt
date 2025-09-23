package woowacourse.payments.data.model.request

data class NewCardRequest(
    override val numberDigits: String,
    override val expiry: String,
    override val holder: String,
    override val bankType: String,
) : CardRequest

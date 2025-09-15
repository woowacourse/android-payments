package woowacourse.payments.ui.screen.addCard

import woowacourse.payments.R

enum class AddCardError(
    val messageRes: Int,
) {
    CARD_NUMBER_INVALID(R.string.card_number_invalid),
    EXPIRED_INVALID(R.string.expired_invalid),
    OWNER_INVALID(R.string.card_owner_invalid),
    PASSWORD_INVALID(R.string.password_invalid),
}

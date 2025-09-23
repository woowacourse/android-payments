package woowacourse.payments.ui.screen.addCard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R

@Parcelize
enum class AddCardError(
    val messageRes: Int,
) : Parcelable {
    CARD_NUMBER_INVALID(R.string.card_number_invalid),
    EXPIRED_INVALID(R.string.expired_invalid),
    OWNER_INVALID(R.string.card_owner_invalid),
    PASSWORD_INVALID(R.string.password_invalid),
}

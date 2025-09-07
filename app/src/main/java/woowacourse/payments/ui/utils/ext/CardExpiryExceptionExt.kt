package woowacourse.payments.ui.utils.ext

import androidx.annotation.StringRes
import woowacourse.payments.R
import woowacourse.payments.domain.CardExpiryException

@StringRes
fun CardExpiryException.toErrorResourceId(): Int =
    when (this) {
        CardExpiryException.InvalidFormat -> R.string.error_card_expiry_invalid_format
        CardExpiryException.InvalidMonth -> R.string.error_card_expiry_invalid_month
        CardExpiryException.InvalidYear -> R.string.error_card_expiry_invalid_year
        CardExpiryException.Expired -> R.string.error_card_expiry_expired
    }

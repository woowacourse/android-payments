package woowacourse.payments.ui.utils.ext

import androidx.annotation.StringRes
import woowacourse.payments.R
import woowacourse.payments.domain.CardExpiryValidator

@StringRes
fun CardExpiryValidator.toErrorResourceId(): Int =
    when (this) {
        CardExpiryValidator.InvalidFormat -> R.string.validate_card_expiry_invalid_format
        CardExpiryValidator.InvalidMonth -> R.string.validate_card_expiry_invalid_month
        CardExpiryValidator.InvalidYear -> R.string.validate_card_expiry_invalid_year
        CardExpiryValidator.Expired -> R.string.validate_card_expiry_expired
    }

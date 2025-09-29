package woowacourse.payments.ui.common

import androidx.annotation.StringRes
import woowacourse.payments.R
import woowacourse.payments.domain.CardExpirationErrorCode

@StringRes
fun CardExpirationErrorCode.toMessageResource(): Int? =
    when (this) {
        CardExpirationErrorCode.INVALID_LENGTH -> null
        CardExpirationErrorCode.INVALID_FORMAT -> R.string.card_expiration_date_text_field_invalid_format_error_message
        CardExpirationErrorCode.PAST_DATE -> R.string.card_expiration_date_text_field_past_date_error_message
    }

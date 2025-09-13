package woowacourse.payments.ui.mapper

import androidx.annotation.StringRes
import woowacourse.payments.R
import woowacourse.payments.domain.card.ExpireDateStatus.Invalid.ExpireDateInvalidReason

@get:StringRes
val ExpireDateInvalidReason.messageResId: Int
    get() =
        when (this) {
            ExpireDateInvalidReason.INVALID_FORMAT -> R.string.add_card_expire_date_etc_error_message
            ExpireDateInvalidReason.EXPIRED -> R.string.add_card_expire_date_past_error_message
            ExpireDateInvalidReason.INVALID_MONTH -> R.string.add_card_expire_date_month_error_message
        }

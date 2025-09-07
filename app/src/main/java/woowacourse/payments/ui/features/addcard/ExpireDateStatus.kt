package woowacourse.payments.ui.features.addcard

import androidx.annotation.StringRes
import woowacourse.payments.R
import java.time.YearMonth

sealed interface ExpireDateStatus {
    data class Valid(
        val yearMonth: YearMonth,
    ) : ExpireDateStatus

    data object Typing : ExpireDateStatus

    sealed interface Invalid : ExpireDateStatus {
        @get:StringRes
        val messageResId: Int
    }

    data object InvalidFormat : Invalid {
        override val messageResId: Int = R.string.add_card_expire_date_etc_error_message
    }

    data object Expired : Invalid {
        override val messageResId: Int = R.string.add_card_expire_date_past_error_message
    }

    data object InvalidMonth : Invalid {
        override val messageResId: Int = R.string.add_card_expire_date_month_error_message
    }
}

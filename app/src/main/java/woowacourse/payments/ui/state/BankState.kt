package woowacourse.payments.ui.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Banks

@Parcelize
sealed interface BankState : Parcelable {
    data object Empty : BankState

    data class Bank(
        val company: Banks,
    ) : BankState
}

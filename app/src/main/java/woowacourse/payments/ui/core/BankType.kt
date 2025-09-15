package woowacourse.payments.ui.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Banks

@Parcelize
sealed interface BankType : Parcelable {
    data object Empty : BankType

    data class Bank(
        val company: Banks,
    ) : BankType
}

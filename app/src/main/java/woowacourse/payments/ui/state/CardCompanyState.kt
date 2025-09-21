package woowacourse.payments.ui.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardCompany

@Parcelize
sealed interface CardCompanyState : Parcelable {
    data object Empty : CardCompanyState

    data class Selected(
        val company: CardCompany,
    ) : CardCompanyState
}

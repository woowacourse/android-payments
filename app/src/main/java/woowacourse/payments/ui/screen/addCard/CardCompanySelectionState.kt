package woowacourse.payments.ui.screen.addCard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.BankType

@Parcelize
sealed class CardCompanySelectionState : Parcelable {
    data object NotSelected : CardCompanySelectionState()

    data class Selected(
        val bank: BankType,
    ) : CardCompanySelectionState()
}

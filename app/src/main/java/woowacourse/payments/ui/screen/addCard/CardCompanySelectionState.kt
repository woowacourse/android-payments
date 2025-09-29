package woowacourse.payments.ui.screen.addCard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.BankUiModel

@Parcelize
sealed class CardCompanySelectionState : Parcelable {
    data object NotSelected : CardCompanySelectionState()

    data class Selected(
        val bank: BankUiModel,
    ) : CardCompanySelectionState()
}

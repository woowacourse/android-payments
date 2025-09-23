package woowacourse.payments.ui.addcard.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.uimodel.CardInfoUiState
import woowacourse.payments.ui.uimodel.isComplete

enum class ModificationMode {
    ADD_CARD,
    MODIFY_CARD,
    ;

    companion object {
        fun isModificationEnabled(
            previous: CardInfoUiState?,
            current: CardInfoUiState,
        ) = previous != current && current.isComplete()
    }
}

package woowacourse.payments.ui.addcard.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.uimodel.CardInfoUiState
import woowacourse.payments.ui.uimodel.isComplete

sealed class ModificationMode : Parcelable {
    abstract val cardInfo: CardInfoUiState

    @Parcelize
    data class Add(
        override val cardInfo: CardInfoUiState = CardInfoUiState(),
    ) : ModificationMode()

    @Parcelize
    data class Modify(
        override val cardInfo: CardInfoUiState,
        val id: Long = cardInfo.id,
    ) : ModificationMode()

    companion object {
        fun isModificationEnabled(
            previous: CardInfoUiState?,
            current: CardInfoUiState,
        ) = previous != current && current.isComplete()
    }
}

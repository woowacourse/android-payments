package woowacourse.payments.ui.cardform.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class CardAction : Parcelable {
    @Parcelize
    data object Register : CardAction()

    @Parcelize
    data class Modify(
        val cardId: Long,
    ) : CardAction()
}

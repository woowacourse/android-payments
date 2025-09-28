package woowacourse.payments.ui.cards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface CardAction : Parcelable {
    @Parcelize
    data class Add(val cardId: Long) : CardAction

    @Parcelize
    data class Update(val cardId: Long) : CardAction
}
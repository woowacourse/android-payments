package woowacourse.payments.ui.cards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
sealed interface CardAction : Parcelable {
    @Parcelize
    data class Add(val cardUiModel: CardUiModel) : CardAction

    @Parcelize
    data class Update(val cardUiModel: CardUiModel) : CardAction
}
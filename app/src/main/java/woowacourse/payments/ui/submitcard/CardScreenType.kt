package woowacourse.payments.ui.submitcard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
sealed interface CardScreenType : Parcelable {
    val card: CardUiModel

    object AddCard : CardScreenType {
        override val card: CardUiModel = CardUiModel.EMPTY
    }

    class EditCard(
        val index: Int,
        override val card: CardUiModel,
    ) : CardScreenType
}

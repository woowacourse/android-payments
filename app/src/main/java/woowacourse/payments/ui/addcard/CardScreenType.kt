package woowacourse.payments.ui.addcard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
sealed interface CardScreenType : Parcelable {
    val card: CardUiModel

    object New : CardScreenType {
        override val card: CardUiModel = CardUiModel.EMPTY
    }

    class Edit(
        val index: Int,
        override val card: CardUiModel,
    ) : CardScreenType
}

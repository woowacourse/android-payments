package woowacourse.payments.ui.submitcard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
sealed interface CardScreenType : Parcelable {
    object AddCard : CardScreenType

    class EditCard(
        val index: Int,
        val card: CardUiModel,
    ) : CardScreenType
}

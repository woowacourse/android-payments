package woowacourse.payments.ui.newcard.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
sealed interface NewCardStatus : Parcelable {
    data object CreateCard : NewCardStatus
    data class EditCard(val cardUiModel: CardUiModel) : NewCardStatus
}
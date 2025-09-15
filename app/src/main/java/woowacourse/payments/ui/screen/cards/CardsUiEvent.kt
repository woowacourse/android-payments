package woowacourse.payments.ui.screen.cards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface CardsUiEvent : Parcelable {
    data object None : CardsUiEvent

    data object AddCardSuccess : CardsUiEvent

    data object AddCardFailure : CardsUiEvent
}

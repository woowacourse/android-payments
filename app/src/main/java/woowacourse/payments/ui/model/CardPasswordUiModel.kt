package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardPassword

@Parcelize
data class CardPasswordUiModel(
    val password: String = "",
    val state: State = State.NOT_FILLED,
) : Parcelable {
    @IgnoredOnParcel
    val isValid: Boolean = state == State.VALID

    enum class State {
        NOT_FILLED,
        VALID,
        INVALID,
    }

    companion object {
        fun from(cardPassword: CardPassword): CardPasswordUiModel =
            CardPasswordUiModel(
                password = cardPassword.value,
                state = State.VALID,
            )
    }
}

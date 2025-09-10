package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardNumber

@Parcelize
data class CardNumberUiModel(
    val cardNumber: String = "",
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
        fun from(cardNumber: CardNumber): CardNumberUiModel =
            CardNumberUiModel(
                cardNumber = cardNumber.value,
                state = State.VALID,
            )
    }
}

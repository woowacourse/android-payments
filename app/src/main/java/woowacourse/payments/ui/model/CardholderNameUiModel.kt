package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardholderName

@Parcelize
data class CardholderNameUiModel(
    val name: String = "",
    val maxLength: Int = CardholderName.MAX_LENGTH,
    val state: State = State.VALID,
) : Parcelable {
    @IgnoredOnParcel
    val isValid: Boolean = state == State.VALID

    enum class State {
        VALID,
        INVALID,
    }

    companion object {
        fun from(cardholderName: CardholderName): CardholderNameUiModel =
            CardholderNameUiModel(
                name = cardholderName.value.uppercase(),
                maxLength = CardholderName.MAX_LENGTH,
                state = State.VALID,
            )
    }
}

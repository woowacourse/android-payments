package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardholderName

@Parcelize
data class CardholderNameUiModel(
    private val cardholderNameValue: String = "",
    val maxLength: Int,
    val state: State = State.VALID,
) : Parcelable {
    @IgnoredOnParcel
    val cardholderName = cardholderNameValue.uppercase()

    @IgnoredOnParcel
    val isValid: Boolean = state == State.VALID

    enum class State {
        VALID,
        INVALID,
    }

    companion object {
        fun from(cardholderName: CardholderName): CardholderNameUiModel =
            CardholderNameUiModel(
                cardholderNameValue = cardholderName.value,
                maxLength = CardholderName.MAX_LENGTH,
                state = State.VALID,
            )
    }
}

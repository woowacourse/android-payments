package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardholderName

@Parcelize
data class CardholderNameUiModel(
    private val name: String = "",
    val maxLength: Int = 0,
    val state: State = State.VALID,
) : Parcelable {
    @IgnoredOnParcel
    val displayedName = name.uppercase()

    @IgnoredOnParcel
    val isValid: Boolean = state == State.VALID

    enum class State {
        VALID,
        INVALID,
    }

    companion object {
        fun from(cardholderName: CardholderName): CardholderNameUiModel =
            CardholderNameUiModel(
                name = cardholderName.value,
                maxLength = CardholderName.MAX_LENGTH,
                state = State.VALID,
            )
    }
}

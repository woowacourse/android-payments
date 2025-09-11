package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardExpirationDate
import java.time.format.DateTimeFormatter

@Parcelize
data class CardExpirationDateUiModel(
    val expirationDate: String = "",
    val state: State = State.NOT_FILLED,
) : Parcelable {
    @IgnoredOnParcel
    val isValid: Boolean = state == State.VALID

    enum class State {
        NOT_FILLED,
        VALID,
        INVALID_FORMAT,
        EXPIRED,
    }

    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMyy")

        fun from(cardExpirationDate: CardExpirationDate): CardExpirationDateUiModel =
            CardExpirationDateUiModel(
                expirationDate = cardExpirationDate.value.format(formatter),
                state = if (cardExpirationDate.isExpired()) State.EXPIRED else State.VALID,
            )
    }
}

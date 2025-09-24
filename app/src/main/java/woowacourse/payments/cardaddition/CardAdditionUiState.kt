package woowacourse.payments.cardaddition

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.Card
import woowacourse.payments.Card.Companion.EXPIRED_DATE_LENGTH
import woowacourse.payments.CardUiModel
import java.time.Month

@Parcelize
data class CardAdditionUiState(
    val card: CardUiModel = CardUiModel(),
) : Parcelable {
    @IgnoredOnParcel
    val isValidCardNumber: Boolean = card.number.length == Card.NUMBER_LENGTH

    @IgnoredOnParcel
    val isValidExpiredDate: Boolean =
        run {
            if (card.expiredDate.length != EXPIRED_DATE_LENGTH) return@run false

            val month: Int = card.expiredDate.take(2).toIntOrNull() ?: return@run false

            runCatching { Month.of(month) }.isSuccess
        }

    @IgnoredOnParcel
    val isValidPassword: Boolean = card.password.length == Card.PASSWORD_LENGTH

    @IgnoredOnParcel
    val isBankSelected: Boolean = card.bankType != null

    @IgnoredOnParcel
    val canAddCard: Boolean =
        isValidCardNumber && isValidExpiredDate && isValidPassword && isBankSelected
}

package woowacourse.payments.view.cardaddition

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.Card.Companion.EXPIRED_DATE_LENGTH
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.view.CardUiModel
import java.time.Month

@Parcelize
data class CardAdditionUiState(
    val card: CardUiModel = CardUiModel(),
) : Parcelable {
    @IgnoredOnParcel
    val isValidCardNumber: Boolean = runCatching { CardNumber(card.number) }.isSuccess

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

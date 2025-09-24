package woowacourse.payments.view.cardediting

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.view.CardUiModel
import java.time.Month

@Parcelize
data class CardEditingUiState(
    val original: CardUiModel,
    val edited: CardUiModel = original,
) : Parcelable {
    @IgnoredOnParcel
    val isValidCardNumber: Boolean =
        edited.number.length == Card.NUMBER_LENGTH

    @IgnoredOnParcel
    val isValidExpiredDate: Boolean =
        run {
            if (edited.expiredDate.length != Card.EXPIRED_DATE_LENGTH) return@run false

            val month: Int = edited.expiredDate.take(2).toIntOrNull() ?: return@run false
            runCatching { Month.of(month) }.isSuccess
        }

    @IgnoredOnParcel
    val isValidPassword: Boolean =
        edited.password.length == Card.PASSWORD_LENGTH

    @IgnoredOnParcel
    val isBankSelected: Boolean =
        edited.bankType != null

    @IgnoredOnParcel
    val canEditCard: Boolean =
        original != edited && isValidCardNumber && isValidExpiredDate && isValidPassword && isBankSelected
}

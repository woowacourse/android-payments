package woowacourse.payments.view.cardaddition

import YearMonthParser
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.view.CardUiModel

@Parcelize
data class CardAdditionUiState(
    val card: CardUiModel = CardUiModel(),
) : Parcelable {
    @IgnoredOnParcel
    val isValidCardNumber: Boolean = runCatching { CardNumber(card.number) }.isSuccess

    @IgnoredOnParcel
    val isValidExpiredDate: Boolean = YearMonthParser.isValid(card.expiredDate)

    @IgnoredOnParcel
    val isValidPassword: Boolean = runCatching { CardPassword(card.password) }.isSuccess

    @IgnoredOnParcel
    val isBankSelected: Boolean = card.bankType != null

    @IgnoredOnParcel
    val canAddCard: Boolean =
        isValidCardNumber && isValidExpiredDate && isValidPassword && isBankSelected
}

package woowacourse.payments.view.cardediting

import YearMonthParser
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.view.CardUiModel

@Parcelize
data class CardEditingUiState(
    val original: CardUiModel,
    val edited: CardUiModel = original,
) : Parcelable {
    @IgnoredOnParcel
    val isValidCardNumber: Boolean = runCatching { CardNumber(edited.number) }.isSuccess

    @IgnoredOnParcel
    val isValidExpiredDate: Boolean = YearMonthParser.isValid(edited.expiredDate)

    @IgnoredOnParcel
    val isValidPassword: Boolean = runCatching { CardPassword(edited.password) }.isSuccess

    @IgnoredOnParcel
    val isBankSelected: Boolean =
        edited.bankType != null

    @IgnoredOnParcel
    val canEditCard: Boolean =
        original != edited && isValidCardNumber && isValidExpiredDate && isValidPassword && isBankSelected
}

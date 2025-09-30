package woowacourse.payments.view.ui.model

import YearMonthParser
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword

@Parcelize
data class CardUiModel(
    val number: String = "",
    val expiredDate: String = "",
    val holder: String = "",
    val holderMaxLength: Int = 30,
    val password: String = "",
    val bankType: BankTypeUiModel? = null,
) : Parcelable {
    @IgnoredOnParcel
    val isValidCardNumber: Boolean = runCatching { CardNumber(number) }.isSuccess

    @IgnoredOnParcel
    val isValidExpiredDate: Boolean = YearMonthParser.isValid(expiredDate)

    @IgnoredOnParcel
    val isValidPassword: Boolean = runCatching { CardPassword(password) }.isSuccess

    @IgnoredOnParcel
    val isBankSelected: Boolean = bankType != null

    @IgnoredOnParcel
    val isValid: Boolean =
        isValidCardNumber && isValidExpiredDate && isValidPassword && isBankSelected
}

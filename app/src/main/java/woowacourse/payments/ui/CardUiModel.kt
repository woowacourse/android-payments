package woowacourse.payments.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.util.formatCardNumber
import woowacourse.payments.ui.util.formatExpired

@Parcelize
data class CardUiModel(
    val number: String,
    val expired: String,
    val owner: String,
) : Parcelable {
    val maskedNumber get() = formatCardNumber(number)
    val formattedExpired get() = formatExpired(expired)
}

fun Card.toPresentation(): CardUiModel =
    CardUiModel(
        number = number.value,
        expired = expired.value,
        owner = owner.value,
    )

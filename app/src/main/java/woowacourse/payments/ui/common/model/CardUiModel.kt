package woowacourse.payments.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardNumber

@Parcelize
data class CardUiModel(
    val number: String,
    val expirationDate: String,
    val holderName: String? = null,
) : Parcelable

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        number = number.toMaskedString(),
        expirationDate = expirationDate.toDisplayString(),
        holderName = holderName?.value,
    )

private fun CardNumber.toMaskedString(): String =
    numbers
        .map { it.value }
        .joinToString("")
        .chunked(4)
        .mapIndexed { index, chunk -> if (index < 2) chunk else "****" }
        .joinToString(" - ")

private fun CardExpirationDate.toDisplayString(): String {
    val month: String = date.monthValue.toString().padStart(2, '0')
    val year: String = (date.year % 100).toString().padStart(2, '0')
    return "$month / $year"
}

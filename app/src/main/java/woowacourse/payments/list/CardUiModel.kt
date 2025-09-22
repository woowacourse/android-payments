package woowacourse.payments.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardExpiry
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword

@Parcelize
data class CardUiModel(
    val number: String,
    val expiry: String,
    val password: String,
    val name: String? = null,
    val company: CardCompany = CardCompany.NOT_SELECTED,
) : Parcelable

fun CardNumber.toFormattedString(): String {
    val value = this.value.joinToString("") { it.value.toString() }
    val visible = value.substring(0, 8)
    val masked = "*".repeat(8)
    return (visible + masked).chunked(4).joinToString(" - ")
}

fun CardExpiry.toFormattedString(): String {
    val year = this.value.year % 100
    val month = this.value.monthValue.toString().padStart(2, '0').padStart(2, '0')
    return "$month / $year"
}

fun CardPassword.toFormattedString(): String {
    return this.value.joinToString("") { it.value.toString() }.replace(Regex("\\d"), "*")
}

fun Card.toUiModel(): CardUiModel {
    return CardUiModel(
        company = this.company,
        number = this.number.toFormattedString(),
        expiry = this.expiry.toFormattedString(),
        password = password.toFormattedString(),
        name = this.name.value,
    )
}

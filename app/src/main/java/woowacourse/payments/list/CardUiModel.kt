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
) : Parcelable {
    val maskedNumber: String
        get() =
            if (number.isNotEmpty()) {
                (number.take(8) + "*".repeat(8)).chunked(4).joinToString(" - ")
            } else {
                ""
            }

    val maskedExpiry: String
        get() =
            if (expiry.isNotEmpty()) {
                expiry.take(2) + " / " + expiry.takeLast(2)
            } else {
                ""
            }

    val maskedPassword: String
        get() = "****"
}

fun CardNumber.toFormattedString(): String {
    return this.value.joinToString("") { it.value.toString() }
}

fun CardExpiry.toFormattedString(): String {
    val year = this.value.year % 100
    val month = this.value.monthValue.toString().padStart(2, '0')
    val yearStr = year.toString().padStart(2, '0')
    return "$month$yearStr"
}

fun CardPassword.toFormattedString(): String {
    return this.value.joinToString("") { it.value.toString() }
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

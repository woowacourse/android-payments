package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardExpirationDate

@Parcelize
data class CardExpirationDateUiModel(
    val month: String = "",
    val year: String = "",
) : Parcelable {
    fun toCombinedFormat(): String = month + year

    fun toFormattedString(separator: String): String = month + separator + year

    fun toDomain(): CardExpirationDate =
        CardExpirationDate(
            month = month,
            year = year,
        )
}

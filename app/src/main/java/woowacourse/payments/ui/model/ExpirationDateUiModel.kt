package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.Month

@Parcelize
data class ExpirationDateUiModel(
    val value: String,
) : Parcelable {
    fun isValidMonth(): Boolean {
        if (value.length < 2) return true
        return value.substring(0, 2).toInt() in Month.JANUARY.value..Month.DECEMBER.value
    }

    companion object {
        const val EXPIRATION_DATE_LENGTH: Int = 4
    }
}

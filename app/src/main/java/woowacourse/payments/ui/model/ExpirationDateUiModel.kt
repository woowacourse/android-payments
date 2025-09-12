package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.Month

@Parcelize
data class ExpirationDateUiModel(
    val value: String,
) : Parcelable {
    fun isValidMonth(): Boolean {
        val mm = value.take(2)
        val month = mm.toIntOrNull() ?: return false
        return month in Month.JANUARY.value..Month.DECEMBER.value
    }

    companion object {
        const val EXPIRATION_DATE_LENGTH: Int = 4
    }
}

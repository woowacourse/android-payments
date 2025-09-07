package woowacourse.payments.ui.newcard

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.ExpirationDateUiModel

@Parcelize
class ExpirationDateUiState(
    val rawExpirationDate: String,
) : Parcelable {
    @IgnoredOnParcel
    var expirationDate by mutableStateOf(ExpirationDateUiModel(rawExpirationDate))
        private set

    @IgnoredOnParcel
    var isError: Boolean by mutableStateOf(this.expirationDate.isValidMonth())
        private set

    fun onValueChanged(value: String) {
        val newCardNumber = ExpirationDateUiModel(value)
        expirationDate = newCardNumber
        isError = newCardNumber.isValidMonth()
    }
}

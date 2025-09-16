package woowacourse.payments.ui.addcard.model

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize

@Parcelize
data class VendorModalUiState(
    private val vendorModalUiModel: VendorModalUiModel = VendorModalUiModel(),
) : Parcelable {
    var isVisible by mutableStateOf(vendorModalUiModel.isVisible)
        private set

    fun show() {
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }
}
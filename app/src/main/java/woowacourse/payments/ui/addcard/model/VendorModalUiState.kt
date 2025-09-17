package woowacourse.payments.ui.addcard.model

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize
import woowacourse.payments.model.CardVendor
import woowacourse.payments.ui.uimodel.VendorUiModel
import woowacourse.payments.ui.uimodel.toUiModel

@Parcelize
data class VendorModalUiState(
    private val vendorModalUiModel: VendorModalUiModel = VendorModalUiModel(),
) : Parcelable {
    var isVisible by mutableStateOf(vendorModalUiModel.isVisible)
        private set
    val vendors = vendorModalUiModel.vendors
    fun show() {
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }
}
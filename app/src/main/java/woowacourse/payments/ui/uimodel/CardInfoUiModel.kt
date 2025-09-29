package woowacourse.payments.ui.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardInfoUiModel(
    val cardNumber: String = "",
    val expireDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val vendor: VendorUiModel? = null,
    val id: Long = idx++,
) : Parcelable {
    companion object {
        private var idx = 0L
    }
}

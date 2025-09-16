package woowacourse.payments.ui.addcard.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VendorModalUiModel(
    val isVisible: Boolean = true,
) : Parcelable {
}
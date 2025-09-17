package woowacourse.payments.ui.addcard.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.model.CardVendor
import woowacourse.payments.ui.uimodel.VendorUiModel
import woowacourse.payments.ui.uimodel.toUiModel

@Parcelize
data class VendorModalUiModel(
    val isVisible: Boolean = true,
    val vendors: List<VendorUiModel> = CardVendor.entries.map { it.toUiModel() }
) : Parcelable
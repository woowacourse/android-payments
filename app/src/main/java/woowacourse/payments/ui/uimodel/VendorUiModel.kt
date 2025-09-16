package woowacourse.payments.ui.uimodel

import android.content.Context
import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.model.CardVendors

@Parcelize
data class VendorUiModel(
    @DrawableRes val vendorImageId: Int,
    @ColorRes val vendorColorId: Int,
    @StringRes val vendorNameId: Int,
) : Parcelable

fun CardVendors.toUiModel() =
    when (this) {
        CardVendors.BCCard ->
            VendorUiModel(
                R.drawable.img_vendor_bc_card,
                R.color.vendor_bc_card,
                R.string.vendor_bc_card,
            )

        CardVendors.ShinHanCard ->
            VendorUiModel(
                R.drawable.img_vendor_shinhan_card,
                R.color.vendor_shinhan_card,
                R.string.vendor_shinhan_card,
            )
        CardVendors.KakaoBank ->
            VendorUiModel(
                R.drawable.img_vendor_kakao_bank,
                R.color.vendor_kakao_bank,
                R.string.vendor_kakao_bank,
            )
        CardVendors.HyundaiCard ->
            VendorUiModel(
                R.drawable.img_vendor_hyundae_card,
                R.color.vendor_hyundai_card,
                R.string.vendor_hyundai_card,
            )
        CardVendors.WooriCard ->
            VendorUiModel(
                R.drawable.img_vendor_woori_card,
                R.color.vendor_woori_card,
                R.string.vendor_woori_card,
            )
        CardVendors.LotteCard ->
            VendorUiModel(
                R.drawable.img_vendor_lotte_card,
                R.color.vendor_lotte_card,
                R.string.vendor_lotte_card,
            )
        CardVendors.HanaCard ->
            VendorUiModel(
                R.drawable.img_vendor_hana_card,
                R.color.vendor_hana_card,
                R.string.vendor_hana_card,
            )
        CardVendors.KBCard ->
            VendorUiModel(
                R.drawable.img_vendor_kb_card,
                R.color.vendor_kb_card,
                R.string.vendor_kb_card,
            )
    }

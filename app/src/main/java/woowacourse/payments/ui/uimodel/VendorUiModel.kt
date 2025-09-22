package woowacourse.payments.ui.uimodel

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.model.CardVendor

@Parcelize
data class VendorUiModel(
    @DrawableRes val vendorImageId: Int,
    @ColorRes val vendorColorId: Int,
    @StringRes val vendorNameId: Int,
    val vendor: CardVendor,
) : Parcelable

fun CardVendor.toUiModel() =
    when (this) {
        CardVendor.BCCard ->
            VendorUiModel(
                R.drawable.img_vendor_bc_card,
                R.color.vendor_bc_card,
                R.string.vendor_bc_card,
                CardVendor.BCCard,
            )

        CardVendor.ShinHanCard ->
            VendorUiModel(
                R.drawable.img_vendor_shinhan_card,
                R.color.vendor_shinhan_card,
                R.string.vendor_shinhan_card,
                CardVendor.ShinHanCard,
            )

        CardVendor.KakaoBank ->
            VendorUiModel(
                R.drawable.img_vendor_kakao_bank,
                R.color.vendor_kakao_bank,
                R.string.vendor_kakao_bank,
                CardVendor.KakaoBank,
            )

        CardVendor.HyundaiCard ->
            VendorUiModel(
                R.drawable.img_vendor_hyundae_card,
                R.color.vendor_hyundai_card,
                R.string.vendor_hyundai_card,
                CardVendor.HyundaiCard,
            )

        CardVendor.WooriCard ->
            VendorUiModel(
                R.drawable.img_vendor_woori_card,
                R.color.vendor_woori_card,
                R.string.vendor_woori_card,
                CardVendor.WooriCard,
            )

        CardVendor.LotteCard ->
            VendorUiModel(
                R.drawable.img_vendor_lotte_card,
                R.color.vendor_lotte_card,
                R.string.vendor_lotte_card,
                CardVendor.LotteCard,
            )

        CardVendor.HanaCard ->
            VendorUiModel(
                R.drawable.img_vendor_hana_card,
                R.color.vendor_hana_card,
                R.string.vendor_hana_card,
                CardVendor.HanaCard,
            )

        CardVendor.KBCard ->
            VendorUiModel(
                R.drawable.img_vendor_kb_card,
                R.color.vendor_kb_card,
                R.string.vendor_kb_card,
                CardVendor.KBCard,
            )
    }

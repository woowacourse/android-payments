package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.ColorLong
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany

@Parcelize
data class CardCompanyUiModel(
    val company: CardCompany,
    @StringRes val nameRes: Int,
    @DrawableRes val logoRes: Int,
    @ColorLong val cardColor: Long,
) : Parcelable

fun CardCompany.toUiModel(): CardCompanyUiModel =
    when (this) {
        CardCompany.NONE ->
            CardCompanyUiModel(
                CardCompany.NONE,
                R.string.card_company_name_not_selected,
                R.drawable.icon_no_icon,
                0xFF333333,
            )

        CardCompany.BC_CARD ->
            CardCompanyUiModel(
                CardCompany.BC_CARD,
                R.string.card_company_name_bc_card,
                R.drawable.icon_bc_card,
                0xFFF04651,
            )

        CardCompany.SHINHAN_CARD ->
            CardCompanyUiModel(
                CardCompany.SHINHAN_CARD,
                R.string.card_company_name_shinhan_card,
                R.drawable.icon_shinhan_card,
                0xFF0046FF,
            )

        CardCompany.KAKAO_BANK ->
            CardCompanyUiModel(
                CardCompany.KAKAO_BANK,
                R.string.card_company_name_kakao_bank,
                R.drawable.icon_kakao_bank,
                0xFFFFE600,
            )

        CardCompany.HYUNDAI_CARD ->
            CardCompanyUiModel(
                CardCompany.HYUNDAI_CARD,
                R.string.card_company_name_hyundai_card,
                R.drawable.icon_hyundai_card,
                0xFF000000,
            )

        CardCompany.WOORI_CARD ->
            CardCompanyUiModel(
                CardCompany.WOORI_CARD,
                R.string.card_company_name_woori_card,
                R.drawable.icon_woori_card,
                0xFF027BC8,
            )

        CardCompany.LOTTE_CARD ->
            CardCompanyUiModel(
                CardCompany.LOTTE_CARD,
                R.string.card_company_name_lotte_card,
                R.drawable.icon_lotte_card,
                0xFFED1C25,
            )

        CardCompany.HANA_CARD ->
            CardCompanyUiModel(
                CardCompany.HANA_CARD,
                R.string.card_company_name_hana_card,
                R.drawable.icon_hana_card,
                0xFF019490,
            )

        CardCompany.KB_CARD ->
            CardCompanyUiModel(
                CardCompany.KB_CARD,
                R.string.card_company_name_kb_card,
                R.drawable.icon_kb_card,
                0xFF554E45,
            )
    }

package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany

@Parcelize
data class CardCompanyUiModel(
    val company: CardCompany,
    val name: String,
    @DrawableRes val logo: Int,
    val cardColor: Long,
) : Parcelable

fun CardCompany.toUiModel(): CardCompanyUiModel =
    when (this) {
        CardCompany.NONE ->
            CardCompanyUiModel(
                CardCompany.NONE,
                "",
                R.drawable.icon_no_icon,
                0xFF333333,
            )

        CardCompany.BC_CARD ->
            CardCompanyUiModel(
                CardCompany.BC_CARD,
                "BC카드",
                R.drawable.icon_bc_card,
                0xFFF04651,
            )

        CardCompany.SHINHAN_CARD ->
            CardCompanyUiModel(
                CardCompany.SHINHAN_CARD,
                "신한카드",
                R.drawable.icon_shinhan_card,
                0xFF0046FF,
            )

        CardCompany.KAKAO_BANK ->
            CardCompanyUiModel(
                CardCompany.KAKAO_BANK,
                "카카오뱅크",
                R.drawable.icon_kakao_bank,
                0xFFFFE600,
            )

        CardCompany.HYUNDAI_CARD ->
            CardCompanyUiModel(
                CardCompany.HYUNDAI_CARD,
                "현대카드",
                R.drawable.icon_hyundai_card,
                0xFF000000,
            )

        CardCompany.WOORI_CARD ->
            CardCompanyUiModel(
                CardCompany.WOORI_CARD,
                "우리카드",
                R.drawable.icon_woori_card,
                0xFF027BC8,
            )

        CardCompany.LOTTE_CARD ->
            CardCompanyUiModel(
                CardCompany.LOTTE_CARD,
                "롯데카드",
                R.drawable.icon_lotte_card,
                0xFFED1C25,
            )

        CardCompany.HANA_CARD ->
            CardCompanyUiModel(
                CardCompany.HANA_CARD,
                "하나카드",
                R.drawable.icon_hana_card,
                0xFF019490,
            )

        CardCompany.KB_CARD ->
            CardCompanyUiModel(
                CardCompany.KB_CARD,
                "국민카드",
                R.drawable.icon_kb_card,
                0xFF554E45,
            )
    }

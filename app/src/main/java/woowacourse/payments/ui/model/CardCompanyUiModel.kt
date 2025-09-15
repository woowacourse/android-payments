package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany

data class CardCompanyUiModel(
    val name: String,
    @DrawableRes val logoId: Int,
    val cardColor: Color,
)

fun CardCompany.toUiModel(): CardCompanyUiModel =
    when (this) {
        CardCompany.BC_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_bc_card,
                Color(0xFFF04651),
            )

        CardCompany.SHINHAN_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_shinhan_card,
                Color(0xFF0046FF),
            )

        CardCompany.KAKAO_BANK ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_kakao_bank,
                Color(0xFFFFE600),
            )

        CardCompany.HYUNDAI_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_hyundai_card,
                Color(0xFF000000),
            )

        CardCompany.WOORI_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_woori_card,
                Color(0xFF027BC8),
            )

        CardCompany.LOTTE_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_lotte_card,
                Color(0xFFED1C25),
            )

        CardCompany.HANA_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_hana_card,
                Color(0xFF019490),
            )

        CardCompany.KB_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_kb_card,
                Color(0xFF554E45),
            )
    }

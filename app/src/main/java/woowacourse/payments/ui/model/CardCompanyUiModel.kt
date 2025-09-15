package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany

data class CardCompanyUiModel(
    val name: String,
    @DrawableRes val logoId: Int,
)

fun CardCompany.toUiModel(): CardCompanyUiModel =
    when (this) {
        CardCompany.BC_CARD -> CardCompanyUiModel(companyName, R.drawable.icon_bc_card)
        CardCompany.SHINHAN_CARD -> CardCompanyUiModel(companyName, R.drawable.icon_shinhan_card)
        CardCompany.KAKAO_BANK -> CardCompanyUiModel(companyName, R.drawable.icon_kakao_bank)
        CardCompany.HYUNDAI_CARD -> CardCompanyUiModel(companyName, R.drawable.icon_hyundai_card)
        CardCompany.WOORI_CARD -> CardCompanyUiModel(companyName, R.drawable.icon_woori_card)
        CardCompany.LOTTE_CARD -> CardCompanyUiModel(companyName, R.drawable.icon_lotte_card)
        CardCompany.HANA_CARD -> CardCompanyUiModel(companyName, R.drawable.icon_hana_card)
        CardCompany.KB_CARD -> CardCompanyUiModel(companyName, R.drawable.icon_kb_card)
    }

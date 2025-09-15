package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany

data class CardCompanyUiModel(
    val name: String,
    @DrawableRes val logoId: Int,
)

fun CardCompany.toUiModelOrNull(): CardCompanyUiModel? =
    when (name) {
        "BC카드" -> CardCompanyUiModel(name, R.drawable.icon_bc_card)
        "신한카드" -> CardCompanyUiModel(name, R.drawable.icon_shinhan_card)
        "카카오뱅크" -> CardCompanyUiModel(name, R.drawable.icon_kakao_bank)
        "현대카드" -> CardCompanyUiModel(name, R.drawable.icon_hyundai_card)
        "우리카드" -> CardCompanyUiModel(name, R.drawable.icon_woori_card)
        "롯데카드" -> CardCompanyUiModel(name, R.drawable.icon_lotte_card)
        "하나카드" -> CardCompanyUiModel(name, R.drawable.icon_hana_card)
        "국민카드" -> CardCompanyUiModel(name, R.drawable.icon_kb_card)
        else -> null
    }

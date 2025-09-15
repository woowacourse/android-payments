package woowacourse.payments.ui.model

import woowacourse.payments.R

object CardCompanies {
    val cardCompanies: List<CardCompanyUiModel> =
        listOf()
    val bcCard = CardCompanyUiModel("BC카드", R.drawable.icon_bc_card)
    val shinhanCard = CardCompanyUiModel("신한카드", R.drawable.icon_shinhan_card)
    val kakaoBank = CardCompanyUiModel("카카오뱅크", R.drawable.icon_kakao_bank)
    val hyundaiCard = CardCompanyUiModel("현대카드", R.drawable.icon_hyundai_card)
    val wooriCard = CardCompanyUiModel("우리카드", R.drawable.icon_woori_card)
    val lotteCard = CardCompanyUiModel("롯데카드", R.drawable.icon_lotte_card)
    val hanaCard = CardCompanyUiModel("하나카드", R.drawable.icon_hana_card)
    val kbCard = CardCompanyUiModel("국민카드", R.drawable.icon_kb_card)
}

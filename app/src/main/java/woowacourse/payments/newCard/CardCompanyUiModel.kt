package woowacourse.payments.newCard

import woowacourse.payments.domain.CardCompany

data class CardCompanyUiModel(
    val company: CardCompany,
    val displayName: String = "",
)

fun CardCompany.toUiModel(): CardCompanyUiModel =
    when (this) {
        CardCompany.NOT_SELECTED -> CardCompanyUiModel(this, "")
        CardCompany.BC -> CardCompanyUiModel(this, "BC카드")
        CardCompany.SHINHAN -> CardCompanyUiModel(this, "신한카드")
        CardCompany.KAKAO -> CardCompanyUiModel(this, "카카오뱅크")
        CardCompany.HYUNDAI -> CardCompanyUiModel(this, "현대카드")
        CardCompany.WOORI -> CardCompanyUiModel(this, "우리카드")
        CardCompany.LOTTE -> CardCompanyUiModel(this, "롯데카드")
        CardCompany.HANA -> CardCompanyUiModel(this, "하나카드")
        CardCompany.KB -> CardCompanyUiModel(this, "국민카드")
    }
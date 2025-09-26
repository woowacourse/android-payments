import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.model.CardCompanyUiModel

fun CardCompany.toUiModel(): CardCompanyUiModel =
    when (this) {
        CardCompany.BC -> CardCompanyUiModel.BC
        CardCompany.SHINHAN -> CardCompanyUiModel.SHINHAN
        CardCompany.KAKAO -> CardCompanyUiModel.KAKAO
        CardCompany.HYUNDAE -> CardCompanyUiModel.HYUNDAE
        CardCompany.WOORI -> CardCompanyUiModel.WOORI
        CardCompany.LOTTE -> CardCompanyUiModel.LOTTE
        CardCompany.HANA -> CardCompanyUiModel.HANA
        CardCompany.KB -> CardCompanyUiModel.KB
    }

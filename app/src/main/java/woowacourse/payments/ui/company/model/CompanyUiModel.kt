package woowacourse.payments.ui.company.model

import androidx.annotation.DrawableRes
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany

data class CompanyUiModel(
    val name: String,
    @DrawableRes val logo: Int,
)

fun CardCompany.toUiModel(): CompanyUiModel {
    @DrawableRes
    val logoResId: Int =
        when (this) {
            CardCompany.BC -> R.drawable.bc
            CardCompany.SHINHAN -> R.drawable.shinhan
            CardCompany.KAKAO -> R.drawable.kakao
            CardCompany.HYUNDAI -> R.drawable.hyundai
            CardCompany.WOORI -> R.drawable.woori
            CardCompany.LOTTE -> R.drawable.lotte
            CardCompany.HANA -> R.drawable.hana
            CardCompany.KB -> R.drawable.kb
        }
    return CompanyUiModel(
        name = companyName,
        logo = logoResId,
    )
}

package woowacourse.payments.newCard

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany

data class CardCompanyUiModel(
    val company: CardCompany,
    val displayName: String = "",
    @DrawableRes val iconRes: Int,
    val color: Color,
)

fun CardCompany.toUiModel(): CardCompanyUiModel =
    when (this) {
        CardCompany.NOT_SELECTED -> CardCompanyUiModel(this, "", R.drawable.ic_launcher_background, Color(0xFF333333))
        CardCompany.BC -> CardCompanyUiModel(this, "BC카드", R.drawable.ic_bc, Color(0xFFF04651))
        CardCompany.SHINHAN -> CardCompanyUiModel(this, "신한카드", R.drawable.ic_shinhan, Color(0xFF0046FF))
        CardCompany.KAKAO -> CardCompanyUiModel(this, "카카오뱅크", R.drawable.ic_kakao, Color(0xFFFFE600))
        CardCompany.HYUNDAI -> CardCompanyUiModel(this, "현대카드", R.drawable.ic_hyundai, Color(0xFF000000))
        CardCompany.WOORI -> CardCompanyUiModel(this, "우리카드", R.drawable.ic_woori, Color(0xFF007BC8))
        CardCompany.LOTTE -> CardCompanyUiModel(this, "롯데카드", R.drawable.ic_lotte, Color(0xFFED1C24))
        CardCompany.HANA -> CardCompanyUiModel(this, "하나카드", R.drawable.ic_hana, Color(0xFF009490))
        CardCompany.KB -> CardCompanyUiModel(this, "국민카드", R.drawable.ic_kb, Color(0xFF695F54))
    }
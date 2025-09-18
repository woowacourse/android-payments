package woowacourse.payments.ui.common.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.domain.model.CardCompany
import woowacourse.payments.ui.theme.GrayFF333333

enum class CardCompanyUiType(
    val title: String,
    @DrawableRes val icon: Int?,
    val color: Color,
    val origin: CardCompany,
) {
    NOT_SELECTED("", null, GrayFF333333, CardCompany.NONE),
    BC("BC카드", R.drawable.ic_bc, Color(0xFFF04651), CardCompany.BC),
    SHINHAN("신한카드", R.drawable.ic_shinhan, Color(0xFF0046FF), CardCompany.SHINHAN),
    KAKAO("카카오뱅크", R.drawable.ic_kakao, Color(0xFFFFE500), CardCompany.KAKAO),
    HYUNDAI("현대카드", R.drawable.ic_hyundae, Color(0xFF000000), CardCompany.HYUNDAI),
    WOORI("우리카드", R.drawable.ic_woori, Color(0xFF007BC8), CardCompany.WOORI),
    LOTTE("롯데카드", R.drawable.ic_lotte, Color(0xFFED1C24), CardCompany.LOTTE),
    HANA("하나카드", R.drawable.ic_hana, Color(0xFF009490), CardCompany.HANA),
    KB("국민카드", R.drawable.ic_kb, Color(0xFF5D544B), CardCompany.KB),
}

fun CardCompany.toUiType(): CardCompanyUiType = CardCompanyUiType.entries.find { it.origin == this } ?: CardCompanyUiType.NOT_SELECTED

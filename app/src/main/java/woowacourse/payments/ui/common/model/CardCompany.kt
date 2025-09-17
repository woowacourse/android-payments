package woowacourse.payments.ui.common.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.ui.theme.GrayFF333333

enum class CardCompany(
    val title: String,
    @DrawableRes val icon: Int?,
    val color: Color,
) {
    NOT_SELECTED("", null, GrayFF333333),
    BC("BC카드", R.drawable.ic_bc, Color(0xFFF04651)),
    SHINHAN("신한카드", R.drawable.ic_shinhan, Color(0xFF0046FF)),
    KAKAO("카카오뱅크", R.drawable.ic_kakao, Color(0xFFFFE500)),
    HYUNDAI("현대카드", R.drawable.ic_hyundae, Color(0xFF000000)),
    WOORI("우리카드", R.drawable.ic_woori, Color(0xFF007BC8)),
    LOTTE("롯데카드", R.drawable.ic_lotte, Color(0xFFED1C24)),
    HANA("하나카드", R.drawable.ic_hana, Color(0xFF009490)),
    KB("국민카드", R.drawable.ic_kb, Color(0xFF5D544B)),
}

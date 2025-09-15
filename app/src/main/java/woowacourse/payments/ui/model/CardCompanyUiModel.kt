package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R

enum class CardCompanyUiModel(
    val companyName: String,
    val plateColor: Color,
    @DrawableRes
    val iconResId: Int? = null,
    val textColor: Color = Color.White,
) {
    UNKNOWN(
        companyName = "",
        plateColor = woowacourse.payments.ui.theme.UnknownCard,
        iconResId = null,
    ),
    BC(
        companyName = "BC카드",
        plateColor = woowacourse.payments.ui.theme.BC,
        iconResId = R.drawable.ic_bc,
    ),
    SHINHAN(
        companyName = "신한카드",
        plateColor = woowacourse.payments.ui.theme.SHINHAN,
        iconResId = R.drawable.ic_shinhan,
    ),
    KAKAO(
        companyName = "카카오뱅크",
        plateColor = woowacourse.payments.ui.theme.KAKAO,
        iconResId = R.drawable.ic_kakao,
        textColor = Color.Black,
    ),
    HYUNDAI(
        companyName = "현대카드",
        plateColor = woowacourse.payments.ui.theme.HYUNDAI,
        iconResId = R.drawable.ic_hyundai,
    ),
    WOORI(
        companyName = "우리카드",
        plateColor = woowacourse.payments.ui.theme.WOORI,
        iconResId = R.drawable.ic_woori,
    ),
    LOTTE(
        companyName = "롯데카드",
        plateColor = woowacourse.payments.ui.theme.LOTTE,
        iconResId = R.drawable.ic_lotte,
    ),
    HANA(
        companyName = "하나카드",
        plateColor = woowacourse.payments.ui.theme.HANA,
        iconResId = R.drawable.ic_hana,
    ),
    KB(
        companyName = "국민카드",
        plateColor = woowacourse.payments.ui.theme.KB,
        iconResId = R.drawable.ic_kb,
    ),
}

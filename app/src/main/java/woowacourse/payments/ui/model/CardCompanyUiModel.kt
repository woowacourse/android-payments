package woowacourse.payments.ui.model

import androidx.compose.ui.graphics.Color

enum class CardCompanyUiModel(
    val companyName: String,
    val plateColor: Color,
) {
    UNKNOWN(
        companyName = "",
        plateColor = woowacourse.payments.ui.theme.UnknownCard,
    ),
    BC(
        companyName = "BC카드",
        plateColor = woowacourse.payments.ui.theme.BC,
    ),
    SHINHAN(
        companyName = "신한카드",
        plateColor = woowacourse.payments.ui.theme.SHINHAN,
    ),
    KAKAO(
        companyName = "카카오뱅크",
        plateColor = woowacourse.payments.ui.theme.KAKAO,
    ),
    HYUNDAI(
        companyName = "현대카드",
        plateColor = woowacourse.payments.ui.theme.HYUNDAI,
    ),
    WOORI(
        companyName = "우리카드",
        plateColor = woowacourse.payments.ui.theme.WOORI,
    ),
    LOTTE(
        companyName = "롯데카드",
        plateColor = woowacourse.payments.ui.theme.LOTTE,
    ),
    HANA(
        companyName = "하나카드",
        plateColor = woowacourse.payments.ui.theme.HANA,
    ),
    KB(
        companyName = "국민카드",
        plateColor = woowacourse.payments.ui.theme.KB,
    ),
}

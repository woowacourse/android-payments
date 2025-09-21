package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R

enum class CardCompanyUiModel(
    @StringRes val companyName: Int,
    @DrawableRes val companyIcon: Int,
    val color: Color,
) {
    BC(
        companyName = R.string.card_company_bc,
        companyIcon = R.drawable.ic_bc,
        color = Color(0xFFF04651),
    ),
    SHINHAN(
        companyName = R.string.card_company_shinhan,
        companyIcon = R.drawable.ic_shinhan,
        color = Color(0xFF0046ff),
    ),
    KAKAO(
        companyName = R.string.card_company_kakao,
        companyIcon = R.drawable.ic_kakao,
        color = Color(0xFFffe600),
    ),
    HYUNDAI(
        companyName = R.string.card_company_hyundai,
        companyIcon = R.drawable.ic_hyundai,
        color = Color(0xFF000000),
    ),
    WOORI(
        companyName = R.string.card_company_woori,
        companyIcon = R.drawable.ic_woori,
        color = Color(0xFF027bc8),
    ),
    LOTTE(
        companyName = R.string.card_company_lotte,
        companyIcon = R.drawable.ic_lotte,
        color = Color(0xFFed1c23),
    ),
    HANA(
        companyName = R.string.card_company_hana,
        companyIcon = R.drawable.ic_hana,
        color = Color(0xFF009490),
    ),
    KB(
        companyName = R.string.card_company_kb,
        companyIcon = R.drawable.ic_kb,
        color = Color(0xFF695F54),
    ),
}

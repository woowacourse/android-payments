package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R

enum class CardCompanyUiModel(
    @StringRes
    val companyNameResId: Int,
    val plateColor: Color,
    @DrawableRes
    val iconResId: Int? = null,
    val textColor: Color = Color.White,
) {
    UNKNOWN(
        companyNameResId = R.string.card_company_unknown,
        plateColor = woowacourse.payments.ui.theme.UnknownCard,
        iconResId = null,
    ),
    BC(
        companyNameResId = R.string.bc_card_company_name,
        plateColor = woowacourse.payments.ui.theme.BC,
        iconResId = R.drawable.ic_bc,
    ),
    SHINHAN(
        companyNameResId = R.string.shinhan_card_company_name,
        plateColor = woowacourse.payments.ui.theme.SHINHAN,
        iconResId = R.drawable.ic_shinhan,
    ),
    KAKAO(
        companyNameResId = R.string.kakao_card_company_name,
        plateColor = woowacourse.payments.ui.theme.KAKAO,
        iconResId = R.drawable.ic_kakao,
        textColor = Color.Black,
    ),
    HYUNDAI(
        companyNameResId = R.string.hyundai_card_company_name,
        plateColor = woowacourse.payments.ui.theme.HYUNDAI,
        iconResId = R.drawable.ic_hyundai,
    ),
    WOORI(
        companyNameResId = R.string.woori_card_company_name,
        plateColor = woowacourse.payments.ui.theme.WOORI,
        iconResId = R.drawable.ic_woori,
    ),
    LOTTE(
        companyNameResId = R.string.lotte_card_company_name,
        plateColor = woowacourse.payments.ui.theme.LOTTE,
        iconResId = R.drawable.ic_lotte,
    ),
    HANA(
        companyNameResId = R.string.hana_card_company_name,
        plateColor = woowacourse.payments.ui.theme.HANA,
        iconResId = R.drawable.ic_hana,
    ),
    KB(
        companyNameResId = R.string.kb_card_company_name,
        plateColor = woowacourse.payments.ui.theme.KB,
        iconResId = R.drawable.ic_kb,
    ),
}

package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.ui.theme.BCColor
import woowacourse.payments.ui.theme.HanaColor
import woowacourse.payments.ui.theme.HyundaiColor
import woowacourse.payments.ui.theme.KBColor
import woowacourse.payments.ui.theme.KakaoColor
import woowacourse.payments.ui.theme.LotteColor
import woowacourse.payments.ui.theme.ShinhanColor
import woowacourse.payments.ui.theme.UnknownCard
import woowacourse.payments.ui.theme.WooriColor

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
        plateColor = UnknownCard,
        iconResId = null,
    ),
    BC(
        companyNameResId = R.string.bc_card_company_name,
        plateColor = BCColor,
        iconResId = R.drawable.ic_bc,
    ),
    SHINHAN(
        companyNameResId = R.string.shinhan_card_company_name,
        plateColor = ShinhanColor,
        iconResId = R.drawable.ic_shinhan,
    ),
    KAKAO(
        companyNameResId = R.string.kakao_card_company_name,
        plateColor = KakaoColor,
        iconResId = R.drawable.ic_kakao,
        textColor = Color.Black,
    ),
    HYUNDAI(
        companyNameResId = R.string.hyundai_card_company_name,
        plateColor = HyundaiColor,
        iconResId = R.drawable.ic_hyundai,
    ),
    WOORI(
        companyNameResId = R.string.woori_card_company_name,
        plateColor = WooriColor,
        iconResId = R.drawable.ic_woori,
    ),
    LOTTE(
        companyNameResId = R.string.lotte_card_company_name,
        plateColor = LotteColor,
        iconResId = R.drawable.ic_lotte,
    ),
    HANA(
        companyNameResId = R.string.hana_card_company_name,
        plateColor = HanaColor,
        iconResId = R.drawable.ic_hana,
    ),
    KB(
        companyNameResId = R.string.kb_card_company_name,
        plateColor = KBColor,
        iconResId = R.drawable.ic_kb,
    ),
}

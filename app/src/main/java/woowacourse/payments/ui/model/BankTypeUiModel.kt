package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R

enum class BankTypeUiModel(
    val bgColor: Color,
    @StringRes val nameResId: Int?,
    @DrawableRes val logoResId: Int?,
) {
    NOT_SELECTED(
        Color.DarkGray,
        null,
        null,
    ),
    BC(
        Color(0xFFFA3246),
        R.string.issuing_bank_selector_bc_card,
        R.drawable.image_logo_bc_card,
    ),
    HANA(
        Color(0xFF008C8C),
        R.string.issuing_bank_selector_hana_card,
        R.drawable.image_logo_hana_card,
    ),
    HYUNDAI(
        Color.Black,
        R.string.issuing_bank_selector_hyundai_card,
        R.drawable.image_logo_hyundai_card,
    ),
    KAKAO(
        Color(0xFFFEE500),
        R.string.issuing_bank_selector_kakao_card,
        R.drawable.image_logo_kakao_card,
    ),
    KB(
        Color(0xFFFFCC00),
        R.string.issuing_bank_selector_kb_card,
        R.drawable.image_logo_kb_card,
    ),
    LOTTE(
        Color(0xFFDA291C),
        R.string.issuing_bank_selector_lotte_card,
        R.drawable.image_logo_lotte_card,
    ),
    SHINHAN(
        Color(0xFF0046FF),
        R.string.issuing_bank_selector_shinhan_card,
        R.drawable.image_logo_shinhan_card,
    ),
    WOORI(
        Color(0xFF0067AC),
        R.string.issuing_bank_selector_woori_card,
        R.drawable.image_logo_woori_card,
    ),
}

@Composable
fun BankTypeUiModel.toBankName(): String = nameResId?.let { resId -> stringResource(resId) }.orEmpty()

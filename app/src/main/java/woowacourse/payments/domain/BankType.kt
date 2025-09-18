package woowacourse.payments.domain

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import woowacourse.payments.R

enum class BankType(
    @StringRes
    val bankName: Int,
    @DrawableRes
    val bankLogo: Int,
    @ColorInt
    val color: Long,
) {
    NOT_SELECTED(R.string.bank_name_not_selected, R.drawable.bc_card_img, 0xFF333333),
    BC(R.string.bank_name_bc, R.drawable.bc_card_img, 0xFFF04651),
    SINHAN(R.string.bank_name_sinhan, R.drawable.sinhan_card_img, 0xFF0046FF),
    KAKAO(R.string.bank_name_kakao, R.drawable.kakao_card_img, 0xFFFFE300),
    HYUNDE(R.string.bank_name_hyunde, R.drawable.hyunde_card_img, 0xFF000000),
    URI(R.string.bank_name_uri, R.drawable.uri_card_img, 0xFF007BC8),
    LOTTE(R.string.bank_name_lotte, R.drawable.lotte_card_img, 0xFFED1C24),
    HANA(R.string.bank_name_hana, R.drawable.hana_card_img, 0xFF009490),
    KB(R.string.bank_name_kb, R.drawable.kb_card_img, 0xFF554E45),
}

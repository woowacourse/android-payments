package woowacourse.payments.domain

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import woowacourse.payments.R

enum class BankType(
    val bankName: String,
    @DrawableRes
    val bankLogo: Int,
    @ColorInt
    val color: Long,
) {
    NOT_SELECTED("", R.drawable.bc_card_img, 0xFF333333),
    BC("BC카드", R.drawable.bc_card_img, 0xFFF04651),
    SINHAN("신한카드", R.drawable.sinhan_card_img, 0xFF0046FF),
    KAKAO("카카오뱅크", R.drawable.kakao_card_img, 0xFFFFE300),
    HYUNDE("현대카드", R.drawable.hyunde_card_img, 0xFF000000),
    URI("우리카드", R.drawable.uri_card_img, 0xFF007BC8),
    LOTTE("롯데카드", R.drawable.lotte_card_img, 0xFFED1C24),
    HANA("하나카드", R.drawable.hana_card_img, 0xFF009490),
    KB("국민카드", R.drawable.kb_card_img, 0xFF554E45),
}

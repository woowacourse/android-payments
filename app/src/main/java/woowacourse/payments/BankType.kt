package woowacourse.payments

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

enum class BankType(
    val cardName: String,
    @DrawableRes val imageRes: Int,
    @ColorInt val cardColor: Int,
) {
    BC("BC카드", R.drawable.img_bc_logo, R.color.black),
    SHINHAN("신한카드", R.drawable.img_shinhan_logo, R.color.black),
    KAKAO("카카오뱅크", R.drawable.img_kakao_logo, R.color.black),
    HYUNDAI("현대카드", R.drawable.img_hyundai_logo, R.color.black),
    WOORI("우리카드", R.drawable.img_woori_logo, R.color.black),
    LOTTE("롯데카드", R.drawable.img_lotte_logo, R.color.black),
    HANA("하나카드", R.drawable.img_hana_logo, R.color.black),
    KB("국민카드", R.drawable.img_kb_logo, R.color.black),
}

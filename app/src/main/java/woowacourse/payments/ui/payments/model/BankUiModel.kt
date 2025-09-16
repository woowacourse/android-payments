package woowacourse.payments.ui.payments.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import woowacourse.payments.R

enum class BankUiModel(
    val bankName: String,
    @ColorRes val bankColor: Int,
    @DrawableRes val bankLogo: Int,
) {
    NOT_SELECTED("선택안됨", R.color.black, R.drawable.ic_launcher_background),
    BC("BC카드", R.color.teal_200, R.drawable.ic_bc_bank),
    SHINHAN("신한카드", R.color.teal_200, R.drawable.ic_shinhan_bank),
    KAKAO("카카오뱅크", R.color.teal_200, R.drawable.ic_kakao_bank),
    HYUNDAI("현대카드", R.color.teal_200, R.drawable.ic_hyundai_bank),
    WOORI("우리카드", R.color.teal_200, R.drawable.ic_woori_bank),
    LOTTE("롯데카드", R.color.teal_200, R.drawable.ic_lotte_bank),
    HANA("하나카드", R.color.teal_200, R.drawable.ic_hana_bank),
    KB("국민카드", R.color.teal_200, R.drawable.ic_kb_bank),
}
package woowacourse.payments.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.theme.Black80
import woowacourse.payments.ui.theme.Blue60
import woowacourse.payments.ui.theme.Blue80
import woowacourse.payments.ui.theme.Brown80
import woowacourse.payments.ui.theme.Red60
import woowacourse.payments.ui.theme.Red80
import woowacourse.payments.ui.theme.Teal80
import woowacourse.payments.ui.theme.Yellow80

enum class BankViewType(
    @DrawableRes val imageRes: Int?,
    @StringRes val nameRes: Int?,
    val color: Color?,
) {
    BC(R.drawable.img_bc, R.string.bc_card, Red80),
    SHINHAN(R.drawable.img_shinhan, R.string.shinhan_card, Blue80),
    KAKAO(R.drawable.img_kakao, R.string.kako_card, Yellow80),
    HYUNDAI(R.drawable.img_hyundai, R.string.hyundai_card, Black80),
    WOORI(R.drawable.img_woori, R.string.woori_card, Blue60),
    LOTTE(R.drawable.img_lotte, R.string.lotte_card, Red60),
    HANA(R.drawable.img_hana, R.string.hana_card, Teal80),
    KOOKMIN(R.drawable.img_kookmin, R.string.kookmin_card, Brown80),
    NONE(null, null, null),
}

fun BankType.toBankViewType(): BankViewType =
    when (this) {
        BankType.BC -> BankViewType.BC
        BankType.SHINHAN -> BankViewType.SHINHAN
        BankType.KAKAO -> BankViewType.KAKAO
        BankType.HYUNDAI -> BankViewType.HYUNDAI
        BankType.WOORI -> BankViewType.WOORI
        BankType.LOTTE -> BankViewType.LOTTE
        BankType.HANA -> BankViewType.HANA
        BankType.KOOKMIN -> BankViewType.KOOKMIN
        BankType.NONE -> BankViewType.NONE
    }

fun BankViewType.toBankType(): BankType =
    when (this) {
        BankViewType.BC -> BankType.BC
        BankViewType.SHINHAN -> BankType.SHINHAN
        BankViewType.KAKAO -> BankType.KAKAO
        BankViewType.HYUNDAI -> BankType.HYUNDAI
        BankViewType.WOORI -> BankType.WOORI
        BankViewType.LOTTE -> BankType.LOTTE
        BankViewType.HANA -> BankType.HANA
        BankViewType.KOOKMIN -> BankType.KOOKMIN
        BankViewType.NONE -> BankType.NONE
    }

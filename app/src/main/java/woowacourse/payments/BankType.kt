package woowacourse.payments

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
enum class BankType(
    @StringRes val cardNameRes: Int,
    @DrawableRes val imageRes: Int,
    @ColorRes val colorRes: Int,
) : Parcelable {
    BC(
        cardNameRes = R.string.card_name_bc,
        imageRes = R.drawable.img_bc_logo,
        colorRes = R.color.card_bc,
    ),
    SHINHAN(
        cardNameRes = R.string.card_name_shinhan,
        imageRes = R.drawable.img_shinhan_logo,
        colorRes = R.color.card_shinhan,
    ),
    KAKAO(
        cardNameRes = R.string.card_name_kakao,
        imageRes = R.drawable.img_kakao_logo,
        colorRes = R.color.card_kakao,
    ),
    HYUNDAI(
        cardNameRes = R.string.card_name_hyundai,
        imageRes = R.drawable.img_hyundai_logo,
        colorRes = R.color.card_hyundai,
    ),
    WOORI(
        cardNameRes = R.string.card_name_woori,
        imageRes = R.drawable.img_woori_logo,
        colorRes = R.color.card_woori,
    ),
    LOTTE(
        cardNameRes = R.string.card_name_lotte,
        imageRes = R.drawable.img_lotte_logo,
        colorRes = R.color.card_lotte,
    ),
    HANA(
        cardNameRes = R.string.card_name_hana,
        imageRes = R.drawable.img_hana_logo,
        colorRes = R.color.card_hana,
    ),
    KB(
        cardNameRes = R.string.card_name_kb,
        imageRes = R.drawable.img_kb_logo,
        colorRes = R.color.card_kb,
    ),
}

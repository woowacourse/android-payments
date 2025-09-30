package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.domain.BankType

@Parcelize
enum class BankTypeUiModel(
    @StringRes
    val bankName: Int,
    @DrawableRes
    val bankLogo: Int,
    @ColorInt
    val color: Int,
) : Parcelable {
    NOT_SELECTED(R.string.bank_name_not_selected, R.drawable.bc_card_img, 0xFF333333.toInt()),
    BC(R.string.bank_name_bc, R.drawable.bc_card_img, 0xFFF04651.toInt()),
    SINHAN(R.string.bank_name_sinhan, R.drawable.sinhan_card_img, 0xFF0046FF.toInt()),
    KAKAO(R.string.bank_name_kakao, R.drawable.kakao_card_img, 0xFFFFE300.toInt()),
    HYUNDE(R.string.bank_name_hyunde, R.drawable.hyunde_card_img, 0xFF000000.toInt()),
    WOORI(R.string.bank_name_woori, R.drawable.woori_card_img, 0xFF007BC8.toInt()),
    LOTTE(R.string.bank_name_lotte, R.drawable.lotte_card_img, 0xFFED1C24.toInt()),
    HANA(R.string.bank_name_hana, R.drawable.hana_card_img, 0xFF009490.toInt()),
    KB(R.string.bank_name_kb, R.drawable.kb_card_img, 0xFF554E45.toInt()),
    ;

    fun toDomain(): BankType =
        when (this) {
            NOT_SELECTED -> BankType.NOT_SELECTED
            BC -> BankType.BC
            SINHAN -> BankType.SINHAN
            KAKAO -> BankType.KAKAO
            HYUNDE -> BankType.HYUNDE
            WOORI -> BankType.WOORI
            LOTTE -> BankType.LOTTE
            HANA -> BankType.HANA
            KB -> BankType.KB
        }
}

package woowacourse.payments

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.theme.BcCardColor
import woowacourse.payments.ui.theme.HanaCardColor
import woowacourse.payments.ui.theme.HyundaiCardColor
import woowacourse.payments.ui.theme.KakaoCardColor
import woowacourse.payments.ui.theme.KbCardColor
import woowacourse.payments.ui.theme.LotteCardColor
import woowacourse.payments.ui.theme.NotSelectedCardColor
import woowacourse.payments.ui.theme.ShinhanCardColor
import woowacourse.payments.ui.theme.WooriCardColor

@Parcelize
enum class BankType(
    val cardName: String?,
    @DrawableRes val imageRes: Int?,
) : Parcelable {
    NOT_SELECTED(null, null),
    BC("BC카드", R.drawable.img_bc_logo),
    SHINHAN("신한카드", R.drawable.img_shinhan_logo),
    KAKAO("카카오뱅크", R.drawable.img_kakao_logo),
    HYUNDAI("현대카드", R.drawable.img_hyundai_logo),
    WOORI("우리카드", R.drawable.img_woori_logo),
    LOTTE("롯데카드", R.drawable.img_lotte_logo),
    HANA("하나카드", R.drawable.img_hana_logo),
    KB("국민카드", R.drawable.img_kb_logo),
    ;

    val cardColor: Color
        get() =
            when (this) {
                NOT_SELECTED -> NotSelectedCardColor
                BC -> BcCardColor
                SHINHAN -> ShinhanCardColor
                KAKAO -> KakaoCardColor
                HYUNDAI -> HyundaiCardColor
                WOORI -> WooriCardColor
                LOTTE -> LotteCardColor
                HANA -> HanaCardColor
                KB -> KbCardColor
            }
}

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
    NOT_SELECTED(
        cardName = null,
        imageRes = null,
    ),
    BC(
        cardName = "BC카드",
        imageRes = R.drawable.img_bc_logo,
    ),
    SHINHAN(
        cardName = "신한카드",
        imageRes = R.drawable.img_shinhan_logo,
    ),
    KAKAO(
        cardName = "카카오뱅크",
        imageRes = R.drawable.img_kakao_logo,
    ),
    HYUNDAI(
        cardName = "현대카드",
        imageRes = R.drawable.img_hyundai_logo,
    ),
    WOORI(
        cardName = "우리카드",
        imageRes = R.drawable.img_woori_logo,
    ),
    LOTTE(
        cardName = "롯데카드",
        imageRes = R.drawable.img_lotte_logo,
    ),
    HANA(
        cardName = "하나카드",
        imageRes = R.drawable.img_hana_logo,
    ),
    KB(
        cardName = "국민카드",
        imageRes = R.drawable.img_kb_logo,
    ),
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

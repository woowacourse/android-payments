package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.ui.theme.BcSignature
import woowacourse.payments.ui.theme.Gray20
import woowacourse.payments.ui.theme.HanaSignature
import woowacourse.payments.ui.theme.HyundaiSignature
import woowacourse.payments.ui.theme.KakaoSignature
import woowacourse.payments.ui.theme.KbSignature
import woowacourse.payments.ui.theme.LotteSignature
import woowacourse.payments.ui.theme.ShinhanSignature
import woowacourse.payments.ui.theme.WooriSignature

@Parcelize
enum class IssuingBank : Parcelable {
    NOT_SELECTED,
    BC,
    SHINHAN,
    KAKAO,
    HYUNDAI,
    WOORI,
    LOTTE,
    HANA,
    KB,
    ;

    fun getColor(): Color =
        when (this) {
            NOT_SELECTED -> Gray20
            BC -> BcSignature
            SHINHAN -> ShinhanSignature
            KAKAO -> KakaoSignature
            HYUNDAI -> HyundaiSignature
            WOORI -> WooriSignature
            LOTTE -> LotteSignature
            HANA -> HanaSignature
            KB -> KbSignature
        }

    fun getNameResId(): Int? =
        when (this) {
            NOT_SELECTED -> null
            BC -> R.string.issuing_bank_bc
            SHINHAN -> R.string.issuing_bank_shinhan
            KAKAO -> R.string.issuing_bank_kakao
            HYUNDAI -> R.string.issuing_bank_hyundai
            WOORI -> R.string.issuing_bank_woori
            LOTTE -> R.string.issuing_bank_lotte
            HANA -> R.string.issuing_bank_hana
            KB -> R.string.issuing_bank_kb
        }

    fun getIconResId(): Int? =
        when (this) {
            NOT_SELECTED -> null
            BC -> R.drawable.ic_bc
            SHINHAN -> R.drawable.ic_shinhan
            KAKAO -> R.drawable.ic_kakao
            HYUNDAI -> R.drawable.ic_hyundai
            WOORI -> R.drawable.ic_woori
            LOTTE -> R.drawable.ic_lotte
            HANA -> R.drawable.ic_hana
            KB -> R.drawable.ic_kb
        }
}

package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Gray20

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
            BC -> Color(0xFFFA3246)
            SHINHAN -> Color(0xFF0046FF)
            KAKAO -> Color(0xFFFEE500)
            HYUNDAI -> Color.Black
            WOORI -> Color(0xFF0067AC)
            LOTTE -> Color(0xFFDA291C)
            HANA -> Color(0xFF008C8C)
            KB -> Color(0xFFFFCC00)
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

    fun getIconResId(): Int =
        when (this) {
            NOT_SELECTED -> 0
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

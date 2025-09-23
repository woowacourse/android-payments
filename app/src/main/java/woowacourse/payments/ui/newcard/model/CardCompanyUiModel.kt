package woowacourse.payments.ui.newcard.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany

@Parcelize
data class CardCompanyUiModel(
    @StringRes val name: Int,
    @DrawableRes val logo: Int,
    val color: Long,
) : Parcelable

fun CardCompany.toUiModel(): CardCompanyUiModel {
    @StringRes
    val nameResId: Int =
        when (this) {
            CardCompany.BC -> R.string.bc_card
            CardCompany.SHINHAN -> R.string.shinhan_card
            CardCompany.KAKAO -> R.string.kakao_card
            CardCompany.HYUNDAI -> R.string.hyundai_card
            CardCompany.WOORI -> R.string.woori_card
            CardCompany.LOTTE -> R.string.lotte_card
            CardCompany.HANA -> R.string.hana_card
            CardCompany.KB -> R.string.kb_card
        }

    val (@DrawableRes logoResId: Int, color: Long) =
        when (this) {
            CardCompany.BC -> R.drawable.bc to 0xFFF04651
            CardCompany.SHINHAN -> R.drawable.shinhan to 0xFF293A94
            CardCompany.KAKAO -> R.drawable.kakao to 0xFFFAE100
            CardCompany.HYUNDAI -> R.drawable.hyundai to 0xFF000000
            CardCompany.WOORI -> R.drawable.woori to 0xFF2371B3
            CardCompany.LOTTE -> R.drawable.lotte to 0xFFE21E26
            CardCompany.HANA -> R.drawable.hana to 0xFF00908F
            CardCompany.KB -> R.drawable.kb to 0xFF695F54
        }
    return CardCompanyUiModel(
        name = nameResId,
        logo = logoResId,
        color = color,
    )
}

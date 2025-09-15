package woowacourse.payments.ui.company.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany

@Parcelize
data class CompanyUiModel(
    val name: String,
    @DrawableRes val logo: Int,
    val color: Long,
) : Parcelable

fun CardCompany.toUiModel(): CompanyUiModel {
    @DrawableRes
    val logoResId: Int =
        when (this) {
            CardCompany.BC -> R.drawable.bc
            CardCompany.SHINHAN -> R.drawable.shinhan
            CardCompany.KAKAO -> R.drawable.kakao
            CardCompany.HYUNDAI -> R.drawable.hyundai
            CardCompany.WOORI -> R.drawable.woori
            CardCompany.LOTTE -> R.drawable.lotte
            CardCompany.HANA -> R.drawable.hana
            CardCompany.KB -> R.drawable.kb
        }
    return CompanyUiModel(
        name = companyName,
        logo = logoResId,
        color = color(),
    )
}

private fun CardCompany.color(): Long =
    when (this) {
        CardCompany.BC -> 0xFFF04651
        CardCompany.SHINHAN -> 0xFF293A94
        CardCompany.KAKAO -> 0xFFFAE100
        CardCompany.HYUNDAI -> 0xFF000000
        CardCompany.WOORI -> 0xFF2371B3
        CardCompany.LOTTE -> 0xFFE21E26
        CardCompany.HANA -> 0xFF00908F
        CardCompany.KB -> 0xFF695F54
    }

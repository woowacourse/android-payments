package woowacourse.payments.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.CardCompany

@Parcelize
data class CardUiModel(
    val companyName: String,
    val color: Long,
    val number: String,
    val expirationDate: String,
    val holderName: String? = null,
) : Parcelable

fun CardCompany.color(): Long =
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

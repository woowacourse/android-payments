package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import woowacourse.payments.R
import woowacourse.payments.domain.BankType

data class BankUiModel(
    @DrawableRes val image: Int,
    val name: String,
)

fun BankType.toBankUiModel(): BankUiModel? {
    return when (this) {
        BankType.NON -> null
        BankType.BC -> BankUiModel(R.drawable.ic_bc, "BC카드")
        BankType.HANA -> BankUiModel(R.drawable.ic_hana, "하나카드")
        BankType.HYUNDAE -> BankUiModel(R.drawable.ic_hyundae, "현대카드")
        BankType.KAKAO -> BankUiModel(R.drawable.ic_kakao, "카카오뱅크")
        BankType.KB -> BankUiModel(R.drawable.ic_kb, "국민카드")
        BankType.LOTTE -> BankUiModel(R.drawable.ic_lotte, "롯데카드")
        BankType.SHINHAN -> BankUiModel(R.drawable.ic_shinhan, "신한카드")
        BankType.WOORI -> BankUiModel(R.drawable.ic_woori, "우리카드")
    }
}
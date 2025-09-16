package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.theme.BankCardColors

data class BankUiModel(
    val name: String,
    val image: ImageSource,
    val cardColor: Color
)

private data class BankResource(
    @DrawableRes val image: Int,
    val name: String,
    val cardColor: Color
)

fun BankType.toBankUiModel(): BankUiModel? {
    val resource = when (this) {
        BankType.BC -> BankResource(R.drawable.ic_bc, "BC카드", BankCardColors.Bc)
        BankType.HANA -> BankResource(R.drawable.ic_hana, "하나카드", BankCardColors.Hana)
        BankType.HYUNDAE -> BankResource(R.drawable.ic_hyundae, "현대카드", BankCardColors.Hyundae)
        BankType.KAKAO -> BankResource(R.drawable.ic_kakao, "카카오뱅크", BankCardColors.Kakao)
        BankType.KB -> BankResource(R.drawable.ic_kb, "국민카드", BankCardColors.Kb)
        BankType.LOTTE -> BankResource(R.drawable.ic_lotte, "롯데카드", BankCardColors.Lotte)
        BankType.SHINHAN -> BankResource(R.drawable.ic_shinhan, "신한카드", BankCardColors.Shinhan)
        BankType.WOORI -> BankResource(R.drawable.ic_woori, "우리카드", BankCardColors.Woori)
        BankType.NON -> return null
    }
    return BankUiModel(
        name = resource.name,
        image = ImageSource.Resource(id = resource.image),
        cardColor = resource.cardColor
    )
}

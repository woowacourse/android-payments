package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.toArgb
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.BankUiModel.Companion.fromRes
import woowacourse.payments.ui.theme.BankCardColors

@Parcelize
data class BankUiModel(
    val name: String,
    val image: ImageSource,
    val cardColor: ColorSource,
) : Parcelable {
    companion object {
        fun fromRes(
            name: String,
            @DrawableRes resId: Int,
            @ColorInt cardColor: Int,
        ) = BankUiModel(name, ImageSource.Resource(resId), ColorSource.Argb(cardColor))
    }
}

fun BankType.toLocalBankUiModel(): BankUiModel =
    when (this) {
        BankType.BC -> fromRes("BC카드", R.drawable.ic_bc, BankCardColors.Bc.toArgb())
        BankType.HANA -> fromRes("하나카드", R.drawable.ic_hana, BankCardColors.Hana.toArgb())
        BankType.HYUNDAE -> fromRes("현대카드", R.drawable.ic_hyundae, BankCardColors.Hyundae.toArgb())
        BankType.KAKAO -> fromRes("카카오뱅크", R.drawable.ic_kakao, BankCardColors.Kakao.toArgb())
        BankType.KB -> fromRes("국민카드", R.drawable.ic_kb, BankCardColors.Kb.toArgb())
        BankType.LOTTE -> fromRes("롯데카드", R.drawable.ic_lotte, BankCardColors.Lotte.toArgb())
        BankType.SHINHAN -> fromRes("신한카드", R.drawable.ic_shinhan, BankCardColors.Shinhan.toArgb())
        BankType.WOORI -> fromRes("우리카드", R.drawable.ic_woori, BankCardColors.Woori.toArgb())
    }

fun BankUiModel.toBankType(): BankType =
    when (this.name) {
        "BC카드" -> BankType.BC
        "하나카드" -> BankType.HANA
        "현대카드" -> BankType.HYUNDAE
        "카카오뱅크" -> BankType.KAKAO
        "국민카드" -> BankType.KB
        "롯데카드" -> BankType.LOTTE
        "신한카드" -> BankType.SHINHAN
        else -> BankType.WOORI
    }

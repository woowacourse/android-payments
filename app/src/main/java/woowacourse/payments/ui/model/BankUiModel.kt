package woowacourse.payments.ui.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import woowacourse.payments.R
import woowacourse.payments.domain.BankType

@Parcelize
data class BankUiModel(
    val name: String,
    @TypeParceler<Color, ColorParceler> val color: Color,
    @param:DrawableRes val logoRes: Int,
) : Parcelable {
    companion object {
        fun create(
            name: String,
            color: Color,
            logoRes: Int,
        ): BankUiModel =
            BankUiModel(
                name = name,
                color = color,
                logoRes = logoRes,
            )

        val NOT_SELECTED =
            BankUiModel(
                name = "카드사를 선택하세요",
                color = Color.LightGray,
                logoRes = R.drawable.ic_not_visible,
            )
    }
}

object ColorParceler : Parceler<Color> {
    override fun create(parcel: Parcel): Color = Color(parcel.readInt())

    override fun Color.write(
        parcel: Parcel,
        flags: Int,
    ) {
        parcel.writeInt(this.toArgb())
    }
}

fun BankType.toPresentation(): BankUiModel =
    when (this) {
        BankType.BC ->
            BankUiModel.create(
                name = "BC카드",
                color = woowacourse.payments.ui.theme.BC,
                logoRes = R.drawable.img_bc,
            )

        BankType.SHINHAN ->
            BankUiModel.create(
                name = "신한카드",
                color = woowacourse.payments.ui.theme.SHINHAN,
                logoRes = R.drawable.img_shinhan,
            )

        BankType.KAKAOBANK ->
            BankUiModel.create(
                name = "카카오뱅크",
                color = woowacourse.payments.ui.theme.KAKAO,
                logoRes = R.drawable.img_kakaobank,
            )

        BankType.HYUNDAI ->
            BankUiModel.create(
                name = "현대카드",
                color = woowacourse.payments.ui.theme.HYUNDAI,
                logoRes = R.drawable.img_hyundai,
            )

        BankType.WOORI ->
            BankUiModel.create(
                name = "우리카드",
                color = woowacourse.payments.ui.theme.WOORI,
                logoRes = R.drawable.img_woori,
            )

        BankType.LOTTE ->
            BankUiModel.create(
                name = "롯데카드",
                color = woowacourse.payments.ui.theme.LOTTE,
                logoRes = R.drawable.img_lotte,
            )

        BankType.HANA ->
            BankUiModel.create(
                name = "하나카드",
                color = woowacourse.payments.ui.theme.HANA,
                logoRes = R.drawable.img_hana,
            )

        BankType.KB ->
            BankUiModel.create(
                name = "KB카드",
                color = woowacourse.payments.ui.theme.KB,
                logoRes = R.drawable.img_kb,
            )
    }

package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.domain.BankType

@Parcelize
data class BankUiModel(
    val name: String,
    val color: Int,
    val logoRes: Int,
) : Parcelable {
    val backgroundColor: Color
        get() = Color(color)

    companion object {
        fun create(
            name: String,
            color: Color,
            logoRes: Int,
        ): BankUiModel =
            BankUiModel(
                name = name,
                color = color.toArgb(),
                logoRes = logoRes,
            )
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

        BankType.NOT_SELECTED ->
            BankUiModel.create(
                name = "",
                color = woowacourse.payments.ui.theme.GRAY,
                logoRes = R.drawable.ic_not_visible,
            )
    }

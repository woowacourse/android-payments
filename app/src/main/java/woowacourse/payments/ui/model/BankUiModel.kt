package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.toArgb
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Gray300

@Parcelize
data class BankUiModel(
    @DrawableRes val logoRes: Int,
    @StringRes val nameRes: Int,
    @ColorInt val colorInt: Int,
    val isSelected: Boolean = true,
) : Parcelable {
    companion object {
        val PlaceHolder =
            BankUiModel(
                logoRes = R.drawable.ic_empty,
                nameRes = R.string.card_name_empty,
                colorInt = Gray300.toArgb(),
                isSelected = false,
            )
    }
}

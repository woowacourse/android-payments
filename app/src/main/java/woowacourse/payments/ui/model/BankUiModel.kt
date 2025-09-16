package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class BankUiModel(
    @DrawableRes val logoRes: Int,
    @StringRes val nameRes: Int,
    @ColorInt val colorInt: Int,
    val isSelected: Boolean = true,
) : Parcelable

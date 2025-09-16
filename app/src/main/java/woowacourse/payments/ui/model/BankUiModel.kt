package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class BankUiModel(
    @DrawableRes val logoRes: Int,
    @StringRes val nameRes: Int,
    val color: Color,
)

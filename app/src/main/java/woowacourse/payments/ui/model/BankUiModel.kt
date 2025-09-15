package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BankUiModel(
    @DrawableRes val logoRes: Int,
    @StringRes val nameRes: Int,
)

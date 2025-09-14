package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.BankType

@Parcelize
data class Bank(
    val type: BankType = BankType.NOT_SELECTED,
    @DrawableRes val icon: Int = 0,
) : Parcelable

package woowacourse.payments.domain.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bank(
    val type: BankType = BankType.NOT_SELECTED,
    @DrawableRes val icon: Int = 0,
) : Parcelable

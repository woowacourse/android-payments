package woowacourse.payments.domain.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bank(
    val type: BankType = BankType.NONE,
    @DrawableRes val icon: Int = 0,
) : Parcelable

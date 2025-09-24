package woowacourse.payments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardUiModel(
    val number: String = "",
    val expiredDate: String = "",
    val holder: String = "",
    val holderMaxLength: Int = 30,
    val password: String = "",
    val bankType: BankType? = null,
) : Parcelable

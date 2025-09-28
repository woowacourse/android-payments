package woowacourse.payments.ui.newcard.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.model.Bank

@Parcelize
data class NewCardStateHolderSnapshot(
    val id: Int,
    val cardNumber: String,
    val cardHolder: String,
    val rawExpirationDate: String,
    val password: String,
    val bank: Bank,
) : Parcelable

package woowacourse.payments.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.model.CardCompany

@Parcelize
data class Card(
    val number: String = "",
    val expiredDate: String = "",
    val ownerName: String? = null,
    val password: String = "",
    val cardCompany: CardCompany,
) : Parcelable

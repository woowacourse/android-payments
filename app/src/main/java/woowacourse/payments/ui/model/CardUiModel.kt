package woowacourse.payments.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card

@Parcelize
data class CardUiModel(
    val number: String,
    val expirationDate: String,
    val cardHolderName: String,
) : Parcelable

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        number = this.number.number,
        expirationDate = this.expirationDate.expirationDate,
        cardHolderName = this.cardHolderName.cardHolderName,
    )

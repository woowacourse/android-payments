package woowacourse.payments.cards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.Card

@Parcelize
data class CardsUiState(
    val cards: List<Card> = emptyList(),
) : Parcelable

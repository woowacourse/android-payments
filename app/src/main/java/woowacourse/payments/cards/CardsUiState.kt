package woowacourse.payments.cards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.CardUiModel

@Parcelize
data class CardsUiState(
    val cards: List<CardUiModel> = emptyList(),
) : Parcelable

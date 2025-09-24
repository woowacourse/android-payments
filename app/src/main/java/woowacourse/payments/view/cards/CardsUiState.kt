package woowacourse.payments.view.cards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.view.CardUiModel

@Parcelize
data class CardsUiState(
    val cards: List<CardUiModel> = emptyList(),
) : Parcelable

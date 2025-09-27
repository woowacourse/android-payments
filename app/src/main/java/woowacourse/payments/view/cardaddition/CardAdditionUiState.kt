package woowacourse.payments.view.cardaddition

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.view.CardUiModel

@Parcelize
data class CardAdditionUiState(
    val card: CardUiModel = CardUiModel(),
) : Parcelable

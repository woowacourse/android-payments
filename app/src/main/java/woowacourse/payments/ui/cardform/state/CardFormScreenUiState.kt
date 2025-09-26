package woowacourse.payments.ui.cardform.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
data class CardFormScreenUiState(
    val card: CardUiModel = CardUiModel(),
    val isBottomSheetOpen: Boolean = true,
) : Parcelable

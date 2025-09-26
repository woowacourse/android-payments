package woowacourse.payments.ui.registration.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
data class CardRegistrationScreenUiState(
    val card: CardUiModel = CardUiModel(),
    val isBottomSheetOpen: Boolean = true,
) : Parcelable

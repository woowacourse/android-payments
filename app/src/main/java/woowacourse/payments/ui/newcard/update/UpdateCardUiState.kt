package woowacourse.payments.ui.newcard.update

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.newcard.state.NewCardContentUiState

@Parcelize
data class UpdateCardUiState(
    val newCardContentUiState: NewCardContentUiState = NewCardContentUiState(),
) : Parcelable
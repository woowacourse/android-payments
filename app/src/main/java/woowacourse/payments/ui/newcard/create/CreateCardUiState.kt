package woowacourse.payments.ui.newcard.create

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.newcard.state.NewCardContentUiState

@Parcelize
data class CreateCardUiState(
    val newCardContentUiState: NewCardContentUiState = NewCardContentUiState(),
) : Parcelable

package woowacourse.payments.view.cardediting

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.payments.view.ui.model.CardUiModel

@Parcelize
data class CardEditingUiState(
    val original: CardUiModel,
    val edited: CardUiModel = original,
) : Parcelable {
    @IgnoredOnParcel
    val canEditCard: Boolean = original != edited && edited.isValid
}

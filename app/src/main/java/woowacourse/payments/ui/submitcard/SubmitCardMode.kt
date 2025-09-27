package woowacourse.payments.ui.submitcard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.model.CardUiModel

@Parcelize
sealed interface SubmitCardMode : Parcelable {
    object Add : SubmitCardMode

    class Edit(
        val index: Int,
        val card: CardUiModel,
    ) : SubmitCardMode
}

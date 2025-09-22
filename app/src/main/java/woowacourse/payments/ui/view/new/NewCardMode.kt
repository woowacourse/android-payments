package woowacourse.payments.ui.view.new

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.ui.serialization.SerializationCard

@Parcelize
sealed interface NewCardMode : Parcelable {
    data object Add : NewCardMode

    data class Modify(
        val card: SerializationCard,
        val index: Int,
    ) : NewCardMode
}

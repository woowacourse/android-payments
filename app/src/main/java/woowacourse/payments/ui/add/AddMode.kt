package woowacourse.payments.ui.add

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class AddMode : Parcelable {
    data object Create : AddMode()

    data class Edit(
        val cardId: String,
    ) : AddMode()
}

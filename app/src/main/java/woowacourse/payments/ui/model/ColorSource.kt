package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.ColorInt
import kotlinx.parcelize.Parcelize

sealed interface ColorSource : Parcelable {
    @Parcelize
    data class Argb(
        @ColorInt val color: Int,
    ) : ColorSource
}

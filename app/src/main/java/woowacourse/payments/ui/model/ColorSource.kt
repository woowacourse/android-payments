package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.ColorInt
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface ColorSource : Parcelable {
    data class Argb(@ColorInt val color: Int) : ColorSource
}
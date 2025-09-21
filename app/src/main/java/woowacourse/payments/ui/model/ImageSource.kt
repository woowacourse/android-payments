package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

sealed interface ImageSource : Parcelable {
    @Parcelize
    data class Resource(
        @DrawableRes val id: Int,
    ) : ImageSource
}

package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface ImageSource : Parcelable {
    data class Resource(@DrawableRes val id: Int) : ImageSource
}

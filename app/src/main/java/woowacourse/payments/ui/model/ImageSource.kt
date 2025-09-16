package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes

sealed interface ImageSource {
    data class Resource(@DrawableRes val id: Int) : ImageSource
    data class Url(val url: String) : ImageSource
}

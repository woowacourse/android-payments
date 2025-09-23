package woowacourse.payments.ui.util

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import woowacourse.payments.ui.uimodel.CardInfoUiState

inline fun <reified T : Parcelable> Intent.getParcelableCompat(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(key) as? T
    }

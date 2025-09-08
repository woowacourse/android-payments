package woowacourse.payments.ui.common.extension

import android.content.Intent
import android.os.Build
import android.os.Parcelable

inline fun <reified T : Parcelable> Intent?.getParcelableCompat(key: String): T {
    val result =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this?.getParcelableExtra(key, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            this?.getParcelableExtra<T>(key)
        }
    return result ?: error("Parcelable extra '$key' not found in intent")
}

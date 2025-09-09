package woowacourse.payments.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable

inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(key) as? T
    }

inline fun <reified T : Parcelable> Bundle.getParcelableList(key: String): List<T> =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getParcelableArrayList(key, T::class.java) ?: emptyList()
    } else {
        @Suppress("DEPRECATION")
        this.getParcelableArrayList<T>(key) ?: emptyList()
    }

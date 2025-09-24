package woowacourse.payments

import android.content.Intent
import android.os.Build
import android.os.Parcelable

inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(key)
    }

const val EXTRA_CARD = "woowacourse.payments.CARD"
const val EXTRA_OLD_CARD = "woowacourse.payments.OLD_CARD"
const val EXTRA_NEW_CARD = "woowacourse.payments.NEW_CARD"

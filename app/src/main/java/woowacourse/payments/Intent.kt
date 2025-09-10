package woowacourse.payments

import android.content.Intent
import android.os.Build

inline fun <reified T> Intent.getParcelableCompat(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
    } else {
        getParcelableExtra(key)
    }

const val EXTRA_CARD = "woowacourse.payments.CARD"

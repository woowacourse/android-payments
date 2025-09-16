package woowacourse.payments

import android.content.Intent
import android.os.Build
import android.os.Parcelable

object IntentCompat {
    @Suppress("DEPRECATION")
    inline fun <reified T : Parcelable> getParcelableExtra(
        intent: Intent,
        key: String,
    ): T? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(key, T::class.java)
        } else {
            intent.getParcelableExtra(key)
        }
}

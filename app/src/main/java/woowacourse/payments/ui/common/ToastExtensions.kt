package woowacourse.payments.ui.common

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(
    @StringRes messageResource: Int,
    duration: Int = Toast.LENGTH_SHORT,
) {
    Toast.makeText(this, getString(messageResource), duration).show()
}

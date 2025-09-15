package woowacourse.payments.ui.common.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(
    @StringRes messageResId: Int,
) = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

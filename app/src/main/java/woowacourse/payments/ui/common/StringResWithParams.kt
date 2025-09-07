package woowacourse.payments.ui.common

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes

class StringResWithParams(
    @StringRes private val resId: Int,
    private vararg val params: Any,
) {
    fun asString(context: Context): String = context.getString(resId, *processParams(context).toTypedArray())

    private fun processParams(context: Context) =
        params.map { param ->
            when (param) {
                is Int -> processIntParams(context, param)
                is StringResWithParams -> param.asString(context)
                else -> param
            }
        }

    private fun processIntParams(
        context: Context,
        param: Int,
    ) = try {
        context.resources.getString(param)
    } catch (exception: Resources.NotFoundException) {
        param
    }
}

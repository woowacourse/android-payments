package woowacourse.payments.ui.extension

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun Modifier.semanticsContentDescription(
    @StringRes contentDescriptionResId: Int,
): Modifier {
    val context = LocalContext.current
    return semantics {
        contentDescription = context.getString(contentDescriptionResId)
    }
}

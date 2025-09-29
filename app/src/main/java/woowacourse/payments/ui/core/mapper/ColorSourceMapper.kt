package woowacourse.payments.ui.core.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import woowacourse.payments.ui.model.ColorSource

@Composable
fun ColorSource.asColor(): Color =
    when (this) {
        is ColorSource.Argb -> Color(color)
    }

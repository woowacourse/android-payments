package woowacourse.payments.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

object ThemeColor {
    val inputHintColor: Color
        @Composable
        get() = if (isSystemInDarkTheme()) Color.White else LightGray
}

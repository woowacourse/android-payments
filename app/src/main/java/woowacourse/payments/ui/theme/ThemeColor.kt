package woowacourse.payments.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object ThemeColor {
    val inputHintColor: Color
        @Composable
        get() = if (isSystemInDarkTheme()) Color.White else LightGray
}

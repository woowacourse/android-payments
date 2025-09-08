package woowacourse.payments.ui.newcard.components

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import woowacourse.payments.designsystem.theme.Black
import woowacourse.payments.designsystem.theme.GrayHint
import woowacourse.payments.designsystem.theme.GrayOutline
import woowacourse.payments.designsystem.theme.GrayText

@Composable
fun formTextFieldColors(
    border: Color = GrayOutline,
    label: Color = GrayText,
    hint: Color = GrayHint,
    text: Color = Black,
    cursor: Color = Black,
): TextFieldColors =
    OutlinedTextFieldDefaults.colors(
        focusedBorderColor = GrayOutline,
        unfocusedBorderColor = GrayOutline,
        disabledBorderColor = GrayOutline,
        errorBorderColor = GrayOutline,
        focusedLabelColor = GrayText,
        unfocusedLabelColor = GrayText,
        focusedPlaceholderColor = GrayHint,
        unfocusedPlaceholderColor = GrayHint,
        focusedTextColor = Black,
        unfocusedTextColor = Black,
        cursorColor = Black,
    )

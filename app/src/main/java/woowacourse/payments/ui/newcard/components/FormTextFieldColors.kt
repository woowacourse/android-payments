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
        focusedBorderColor = border,
        unfocusedBorderColor = border,
        disabledBorderColor = border,
        errorBorderColor = border,
        focusedLabelColor = label,
        unfocusedLabelColor = label,
        focusedPlaceholderColor = hint,
        unfocusedPlaceholderColor = hint,
        focusedTextColor = text,
        unfocusedTextColor = text,
        cursorColor = cursor,
    )

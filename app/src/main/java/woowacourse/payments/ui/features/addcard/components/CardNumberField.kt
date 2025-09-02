package woowacourse.payments.ui.features.addcard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.ThemeColor.inputHintColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        enabled = true,
        textStyle =
            TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
            ),
        label = {
            Text(
                text = "카드 번호",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        placeholder = {
            Text(
                text = "0000 - 0000 - 0000 - 0000",
                fontSize = 16.sp,
                color = inputHintColor,
            )
        },
        colors =
            OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.outline,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            ),
    )
}

@Preview(showBackground = true)
@Composable
fun CardNumberFieldPreview() {
    val text: MutableState<String> = remember { mutableStateOf("") }
    AndroidpaymentsTheme(dynamicColor = false) {
        CardNumberField(
            value = text.value,
            onValueChange = {
                text.value = it
            },
        )
    }
}

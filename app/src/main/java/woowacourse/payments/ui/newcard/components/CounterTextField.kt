package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.designsystem.theme.GrayText

@Composable
fun CounterTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    maxLength: Int,
    showCounter: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    colors: TextFieldColors = FormTextFieldColors(),
    onImeAction: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it.take(maxLength)) },
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        supportingText = {
            if (showCounter) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "${value.length}/$maxLength",
                        color = GrayText,
                    )
                }
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions =
            KeyboardActions(
                onNext = { onImeAction() },
                onDone = { onImeAction() },
            ),
        singleLine = true,
        colors = colors,
    )
}

@Preview
@Composable
private fun CounterTextFieldPreview() {
    CounterTextField(
        value = "1234",
        onValueChange = {},
        label = "카드 번호",
        placeholder = "0000 - 0000 - 0000 - 0000",
        maxLength = 16,
    )
}

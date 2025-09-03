package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DigitsTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    maxLength: Int,
    colors: TextFieldColors = formTextFieldColors(),
    format: (String) -> String = { it },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onImeAction: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val displayed = format(value)
    OutlinedTextField(
        modifier = modifier,
        value = displayed,
        onValueChange = { new ->
            val filtered = filterLimitedDigits(new, maxLength)
            if (filtered != value) onValueChange(filtered)
        },
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        visualTransformation = VisualTransformation.None,
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

private fun filterLimitedDigits(
    input: String,
    max: Int,
): String = input.filter(Char::isDigit).take(max)

@Preview
@Composable
private fun DigitsTextFieldPreview() {
    DigitsTextField(
        value = "1234",
        onValueChange = {},
        label = "카드 번호",
        placeholder = "0000 - 0000 - 0000 - 0000",
        maxLength = 16,
        format = { it.chunked(4).joinToString(" - ") },
    )
}

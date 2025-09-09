package woowacourse.payments.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.InputMask
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun DigitTextField(
    text: String,
    onValueChange: (String) -> Unit,
    label: String,
    hint: String,
    errorMessage: String,
    modifier: Modifier = Modifier,
    maxLength: Int = Int.MAX_VALUE,
    mask: InputMask = InputMask.None,
    imeAction: ImeAction = ImeAction.Done,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            onValueChange(newText.filter { it.isDigit() }.take(maxLength))
        },
        label = { Text(text = label) },
        placeholder = { Text(text = hint) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction,
            ),
        isError = isError,
        supportingText = { if (isError) Text(text = errorMessage) },
        visualTransformation = VisualTransformation { mask.apply(it) },
    )
}

@Preview
@Composable
private fun DigitTextFieldPreview() {
    AndroidpaymentsTheme {
        DigitTextField(
            text = "",
            onValueChange = {},
            label = "카드 번호",
            hint = "0000 - 0000 - 0000 - 0000",
            maxLength = 16,
            mask = InputMask.CardNumber,
            errorMessage = "카드 번호는 16자입니다.",
            isError = false,
        )
    }
}

package woowacourse.payments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun DigitTextField(
    label: String,
    hint: String,
    errorMessage: String,
    modifier: Modifier = Modifier,
    maxLength: Int = Int.MAX_VALUE,
    mask: InputMask = InputMask.None,
    imeAction: ImeAction = ImeAction.Done,
) {
    var number by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = number,
        onValueChange = { newText ->
            val filtered = newText.filter { it.isDigit() }
            val newNumber = filtered.take(maxLength)
            number = newNumber
            val lengthError = (maxLength != Int.MAX_VALUE && newNumber.length < maxLength)
            val expiryError = (mask == InputMask.Expiry && !ExpiryValidator.isValidExpiry(number))
            isError = lengthError || expiryError
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
            label = "카드 번호",
            hint = "0000 - 0000 - 0000 - 0000",
            maxLength = 16,
            mask = InputMask.CardNumber,
            errorMessage = "카드 번호는 16자입니다.",
        )
    }
}

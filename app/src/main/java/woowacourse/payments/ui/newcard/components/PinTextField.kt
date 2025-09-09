package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

private const val MAX_PIN_LENGTH = 4

@Composable
fun PinTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            val onlyDigits = input.filter { it.isDigit() }
            val limited = onlyDigits.take(MAX_PIN_LENGTH)
            onValueChange(limited)
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text(stringResource(id = R.string.new_card_pin_label)) },
        placeholder = { Text(stringResource(id = R.string.new_card_pin_hint)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun PinTextFieldPreview() {
    PinTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier,
    )
}

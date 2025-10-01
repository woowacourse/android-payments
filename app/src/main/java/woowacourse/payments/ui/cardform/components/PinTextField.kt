package woowacourse.payments.ui.cardform.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.cardform.model.PIN_MAX

@Composable
fun PinTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit = {},
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            val onlyDigits = input.filter { it.isDigit() }.take(PIN_MAX)
            onValueChange(onlyDigits)
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text(stringResource(id = R.string.card_pin_label)) },
        placeholder = { Text(stringResource(id = R.string.card_pin_hint)) },
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done,
            ),
        keyboardActions = KeyboardActions(onDone = { onImeAction() }),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        colors = colors,
    )
}

@Preview(showBackground = true)
@Composable
private fun PinTextFieldPreview() {
    AndroidpaymentsTheme {
        PinTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier,
        )
    }
}

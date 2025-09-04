package woowacourse.payments.ui.newcard.components

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

@Composable
fun CardPasswordTextField(
    cardPassword: String,
    maxLength: Int,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = cardPassword,
        onValueChange = { text: String ->
            if (text.length <= maxLength) {
                onValueChange(text)
            }
        },
        label = { Text(stringResource(R.string.card_password)) },
        placeholder = { Text("0000") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun CardPasswordTextFieldPreview() {
    CardPasswordTextField(
        cardPassword = "0000",
        maxLength = 4,
        onValueChange = {},
        modifier = Modifier,
    )
}

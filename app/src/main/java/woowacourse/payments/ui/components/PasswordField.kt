package woowacourse.payments.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import woowacourse.payments.ui.theme.Grey40

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        modifier = modifier,
        value = value,
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = { input ->
            val formatted = input.filter(Char::isDigit).take(4)
            onValueChange(formatted)
        },
        label = { Text("비밀번호") },
        placeholder = {
            Text(
                text = "0000",
                color = Grey40,
            )
        },
        singleLine = true,
    )
}

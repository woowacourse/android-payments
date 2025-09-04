package woowacourse.payments.newcard.component

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { value: String ->
            if (isValidInput(value)) {
                onValueChange(value)
            }
        },
        label = { Text("비밀번호") },
        placeholder = {
            Text(
                text = "0000",
                color = Color.Gray,
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier,
    )
}

private fun isValidInput(password: String): Boolean = password.all { it.isDigit() } && password.length <= 4

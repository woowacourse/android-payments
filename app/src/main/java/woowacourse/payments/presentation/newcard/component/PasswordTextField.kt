package woowacourse.payments.presentation.newcard.component

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun PasswordTextField(modifier: Modifier = Modifier) {
    var password: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = password,
        onValueChange = { value: String ->
            if (isValidInput(value)) {
                password = value
            }
        },
        label = { Text("비밀번호") },
        placeholder = {
            Text(
                text = "0000",
                color = Color.Gray
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier
    )
}

private fun isValidInput(password: String): Boolean {
    return password.all { it.isDigit() } && password.length <= 4
}

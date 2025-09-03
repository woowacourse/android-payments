package woowacourse.payments.ui

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
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PasswordTextField(modifier: Modifier = Modifier) {
    var password by remember { mutableStateOf("") }

    OutlinedTextField(
        value = password,
        onValueChange = { newValue: String ->
            password = newValue
        },
        modifier = modifier,
        label = {
            Text(text = "비밀번호")
        },
        placeholder = {
            Text(text = "0000", color = Color.Gray)
        },
        visualTransformation = PasswordVisualTransformation()
    )
}

@Preview
@Composable
private fun PasswordTextFieldPreview() {
    PasswordTextField()
}
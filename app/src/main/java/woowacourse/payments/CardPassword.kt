package woowacourse.payments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardPassword() {
val cardPassword: MutableState<String> = remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardPassword.value,
        onValueChange = { if (it.length <= 4) cardPassword.value = it },
        label = { Text("비밀번호") },
        placeholder = { Text("0000") },
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        modifier = Modifier.padding(top = 10.dp).padding(horizontal = 24.dp).fillMaxWidth(0.5f)
        )
}

@Composable
@Preview
fun CardPasswordPreview() {
    CardPassword()
}

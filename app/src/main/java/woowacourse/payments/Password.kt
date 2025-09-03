package woowacourse.payments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Password() {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        label = { Text("비밀번호") },
        placeholder = { Text("0000") },
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        modifier = Modifier.padding(top = 10.dp).padding(horizontal = 24.dp).fillMaxWidth(0.5f)
        )
}

@Composable
@Preview
fun PasswordPreview() {
    Password()
}

package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.CardPassword

@Composable
fun CardPasswordTextField(modifier: Modifier = Modifier) {
    var cardPassword: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardPassword,
        onValueChange = { text: String ->
            if (text.length <= CardPassword.CARD_PASSWORD_LENGTH) cardPassword = text
        },
        label = { Text("비밀번호") },
        placeholder = { Text("0000") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun CardPasswordTextFieldPreview() {
    CardPasswordTextField()
}

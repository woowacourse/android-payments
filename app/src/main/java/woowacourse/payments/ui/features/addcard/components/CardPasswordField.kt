package woowacourse.payments.ui.features.addcard.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.components.AppTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        labelText = "비밀번호",
        placeholderText = "0000",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Preview(showBackground = true)
@Composable
fun CardPasswordFieldPreview() {
    var text by remember { mutableStateOf("") }
    AndroidpaymentsTheme(dynamicColor = false) {
        CardPasswordField(
            value = text,
            onValueChange = {
                text = it
            },
        )
    }
}

package woowacourse.payments.card.register.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

@Preview
@Composable
fun CardPasswordTextField(modifier: Modifier = Modifier) {
    var password by remember { mutableStateOf("") }
    val numericRegex = Regex("[^0-9]")

    OutlinedTextField(
        value = password,
        onValueChange = {
            val stripped = numericRegex.replace(it, "")
            password =
                if (stripped.length <= 4) {
                    stripped
                } else {
                    stripped.take(4)
                }
        },
        label = { Text(stringResource(R.string.register_card_password_text_field_label)) },
        placeholder = { Text(stringResource(R.string.register_card_password_text_field_placeholder)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        modifier = modifier,
    )
}

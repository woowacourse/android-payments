package woowacourse.payments.card.register.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import woowacourse.payments.R

@Composable
fun CardPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val numericRegex = Regex("[^0-9]")

    OutlinedTextField(
        value = value,
        onValueChange = {
            val stripped = numericRegex.replace(it, "")
            val limited = stripped.take(4)

            onValueChange(limited)
        },
        modifier = modifier,
        label = { Text(stringResource(R.string.register_card_password_text_field_label)) },
        placeholder = { Text(stringResource(R.string.register_card_password_text_field_placeholder)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
    )
}

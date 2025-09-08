package woowacourse.payments.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Grey10

@Composable
fun CardPassword(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { if (it.length <= CARD_PASSWORD_LENGTH && it.all { it.isDigit() }) onValueChange(it) },
        modifier = modifier,
        label = { Text(stringResource(R.string.card_password)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_password_placeholder),
                color = Grey10,
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
    )
}

@Composable
@Preview
fun CardPasswordPreview() {
    CardPassword(
        value = "",
        onValueChange = {}
    )
}

private const val CARD_PASSWORD_LENGTH = 4

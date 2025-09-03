package woowacourse.payments.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Black49

@Composable
fun CardPasswordTextField(
    maxLength: Int,
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = password,
        onValueChange = { newText ->
            if (newText.length <= maxLength) {
                onPasswordChange(newText)
            }
        },
        placeholder = {
            TextFieldPlaceHolder(textResourceId = R.string.card_password_place_hodler)
        },
        label = {
            Text(
                text = stringResource(R.string.card_password),
                color = Black49
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier
            .fillMaxWidth(0.5f)
    )
}

@Preview(showBackground = true)
@Composable
fun CardPasswordTextFieldPreview() {
    CardPasswordTextField(
        maxLength = 4,
        password = "1234",
        onPasswordChange = {}
    )
}


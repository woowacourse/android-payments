package woowacourse.payments.ui.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import woowacourse.payments.R

private const val CARD_PASSWORD_LENGTH = 4

@Composable
fun CardPasswordTextField(
    cardPassword: String,
    onCardPasswordChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null
) {
    val visualTransformation = remember { PasswordVisualTransformation() }

    OutlinedTextField(
        label = { Text(text = stringResource(R.string.card_password_text_field_label)) },
        placeholder = { Text(text = stringResource(R.string.card_password_text_field_placeholder)) },
        value = cardPassword,
        onValueChange = { newValue ->
            val newPassword = newValue.take(CARD_PASSWORD_LENGTH)
            if (newPassword.isDigitsOnly().not()) return@OutlinedTextField

            onCardPasswordChanged(newPassword)
        },
        isError = errorMessage != null,
        supportingText = { errorMessage?.let { message -> Text(text = message) } },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = visualTransformation,
        colors =
            OutlinedTextFieldDefaults.colors(
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
            ),
        modifier = modifier,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardPasswordTextFieldPreview() {
    Column(modifier = Modifier.padding(12.dp)) {
        CardPasswordTextField(
            cardPassword = "1234",
            onCardPasswordChanged = {},
        )

        CardPasswordTextField(
            cardPassword = "",
            onCardPasswordChanged = {},
        )

        CardPasswordTextField(
            cardPassword = "",
            onCardPasswordChanged = {},
            errorMessage = "유효하지 않은 비밀번호입니다.",
        )
    }
}

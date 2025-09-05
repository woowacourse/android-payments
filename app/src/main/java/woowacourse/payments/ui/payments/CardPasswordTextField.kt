package woowacourse.payments.ui.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import woowacourse.payments.R

private const val CARD_PASSWORD_LENGTH = 4
private const val CARD_PASSWORD_TEXT_FIELD_TEST_TAG = "CardPasswordTextField"

@Composable
fun CardPasswordTextField(
    cardPassword: String,
    onCardPasswordChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val visualTransformation = remember { PasswordVisualTransformation() }

    OutlinedTextField(
        modifier = modifier.testTag(CARD_PASSWORD_TEXT_FIELD_TEST_TAG),
        label = {
            Text(text = stringResource(R.string.card_password_text_field_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.card_password_text_field_placeholder),
                color = Color.Gray,
            )
        },
        value = cardPassword,
        onValueChange = { newValue ->
            if (newValue.length > CARD_PASSWORD_LENGTH) {
                return@OutlinedTextField onCardPasswordChanged(newValue.take(CARD_PASSWORD_LENGTH))
            }
            if (newValue.isDigitsOnly().not()) return@OutlinedTextField
            onCardPasswordChanged(newValue)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = visualTransformation,
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
    }
}

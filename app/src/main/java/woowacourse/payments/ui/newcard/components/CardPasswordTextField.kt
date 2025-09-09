package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import woowacourse.payments.R
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.ui.components.LimitedLengthOutlinedTextField

@Composable
fun CardPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LimitedLengthOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        maxLength = CardPassword.CARD_PASSWORD_LENGTH,
        label = { Text(stringResource(R.string.card_password_label)) },
        placeholder = { Text("0000") },
        isError = value.isNotEmpty() && runCatching { CardPassword(value) }.isFailure,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done,
            ),
        visualTransformation = PasswordVisualTransformation(),
        inputFilter = { it.filter(Char::isDigit) },
        modifier = modifier,
    )
}

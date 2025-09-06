package woowacourse.payments.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import woowacourse.payments.R
import woowacourse.payments.ui.addcard.CardInfoUiState
import woowacourse.payments.ui.addcard.util.PlaceholderTransformation

@Composable
fun PasswordTextField(
    cardInfo: CardInfoUiState,
    modifier: Modifier = Modifier,
    ) {
    OutlinedTextField(
        modifier = modifier,
        singleLine = true,
        value = cardInfo.password,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {
            cardInfo.updateCardInfo(password = it)
        },
        label = { Text(stringResource(R.string.addcard_password_label)) },
        visualTransformation = if (cardInfo.password.isEmpty()) PlaceholderTransformation(
            placeholder = stringResource(R.string.addcard_password_placeholder),
            textColor = colorResource(R.color.payments_placeholder_color)
        ) else PasswordVisualTransformation()
    )
}
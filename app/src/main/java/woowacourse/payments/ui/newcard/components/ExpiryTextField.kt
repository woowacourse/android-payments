package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.common.transformation.GroupedVisualTransformation

private const val EXPIRY_GROUP_SIZE: Int = 2
private const val EXPIRY_SEPARATOR: String = " / "

@Composable
fun ExpiryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit = {},
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(stringResource(id = R.string.new_card_expiry_label)) },
        placeholder = { Text(stringResource(id = R.string.new_card_expiry_hint)) },
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next,
            ),
        keyboardActions = KeyboardActions(onNext = { onImeAction() }),
        visualTransformation = expiryVisualTransformation,
        colors = colors,
    )
}

private val expiryVisualTransformation: VisualTransformation =
    GroupedVisualTransformation(
        groupSize = EXPIRY_GROUP_SIZE,
        separator = EXPIRY_SEPARATOR,
    )

@Preview(showBackground = true)
@Composable
private fun ExpiryTextFieldPreview() {
    AndroidpaymentsTheme {
        ExpiryTextField(
            value = "1225",
            onValueChange = {},
            modifier = Modifier,
        )
    }
}

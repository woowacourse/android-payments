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
import woowacourse.payments.ui.transformation.GroupedVisualTransformation

private const val MAX_EXPIRY_LENGTH = 4
private const val EXPIRY_GROUP_SIZE = 2
private const val EXPIRY_SEPARATOR = " / "

private val expiryVisualTransformation: VisualTransformation =
    GroupedVisualTransformation(
        groupSize = EXPIRY_GROUP_SIZE,
        separator = EXPIRY_SEPARATOR,
    )

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
        onValueChange = { input ->
            val onlyDigits = input.filter { it.isDigit() }
            val limited = onlyDigits.take(MAX_EXPIRY_LENGTH)
            onValueChange(limited)
        },
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

@Preview(showBackground = true)
@Composable
private fun ExpiryTextFieldPreview() {
    ExpiryTextField(
        value = "1225",
        onValueChange = {},
        modifier = Modifier,
    )
}

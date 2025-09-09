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
import woowacourse.payments.ui.transformation.GroupedVisualTransformation

const val CARD_NUMBER_SEPARATOR = " - "
private const val MAX_CARD_NUMBER_LENGTH = 16
private const val CARD_NUMBER_GROUP_SIZE = 4

private val groupedVisualTransformation: VisualTransformation =
    GroupedVisualTransformation(
        groupSize = CARD_NUMBER_GROUP_SIZE,
        separator = CARD_NUMBER_SEPARATOR,
    )

@Composable
fun CardNumberTextField(
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
            val limited = onlyDigits.take(MAX_CARD_NUMBER_LENGTH)
            onValueChange(limited)
        },
        modifier = modifier,
        label = { Text(stringResource(id = R.string.new_card_number_label)) },
        placeholder = { Text(stringResource(id = R.string.new_card_number_hint)) },
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next,
            ),
        keyboardActions = KeyboardActions(onNext = { onImeAction() }),
        visualTransformation = groupedVisualTransformation,
        colors = colors,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberTextFieldPreview() {
    AndroidpaymentsTheme {
        CardNumberTextField(
            value = "1234 - 5678 - 9012 - 3456",
            onValueChange = {},
            modifier = Modifier,
        )
    }
}

package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.newcard.util.transformation.ExpirationDateVisualTransformation

@Composable
fun ExpirationDateTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    label: String,
    placeholder: String,
    maxLength: Int,
) {
    OutlinedTextField(
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value = value,
        label = { Text(text = label) },
        onValueChange = { newValue ->
            if (newValue.length <= maxLength) onValueChange(newValue)
        },
        placeholder = { Text(placeholder) },
        visualTransformation = ExpirationDateVisualTransformation(),
        supportingText = {
            if (!isValid) {
                Text(
                    text = stringResource(R.string.new_card_expiration_date_invalid),
                    color = Color.Red,
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpirationDateTextFieldPreview() {
    ExpirationDateTextField(
        modifier =
            Modifier
                .padding(start = 24.dp, top = 30.dp),
        value = "0611",
        isValid = true,
        onValueChange = {},
        label = stringResource(R.string.new_card_expiration_date_label),
        placeholder = stringResource(R.string.new_card_expiration_date_placeholder),
        maxLength = 4,
    )
}

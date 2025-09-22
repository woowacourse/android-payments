package woowacourse.payments.ui.addcard

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.ui.util.CardExpirationDateVisualTransformation

@Composable
fun ExpirationDateField(
    expirationDate: CardExpirationDate,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = expirationDate.toCombinedFormat(),
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text(stringResource(R.string.add_card_expiration_date_placeholder_text)) },
        label = { Text(stringResource(R.string.add_card_expiration_date_label_text)) },
        isError = expirationDate.isValid().not(),
        supportingText = {
            if (expirationDate.isValid().not()) {
                Text(
                    stringResource(R.string.add_card_expiration_date_supporting_error_text),
                )
                return@OutlinedTextField
            }
            Text(" ")
        },
        visualTransformation = CardExpirationDateVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpirationDateFieldPreview() {
    ExpirationDateField(
        expirationDate = CardExpirationDate.fromRawInput(""),
        onValueChange = {},
    )
}

package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

@Composable
fun CardNumberTextField(
    cardNumber: String,
    maxLength: Int,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = cardNumber,
        onValueChange = { text: String ->
            if (text.length <= maxLength) {
                onValueChange(text)
            }
        },
        label = { Text(stringResource(R.string.card_number)) },
        placeholder = { Text("0000 - 0000 - 0000 - 0000") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberTextFieldPreview() {
    CardNumberTextField(
        cardNumber = "0",
        maxLength = 16,
        onValueChange = {},
        modifier = Modifier,
    )
}

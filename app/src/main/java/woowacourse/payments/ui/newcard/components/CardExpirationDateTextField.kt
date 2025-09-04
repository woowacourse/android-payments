package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

@Composable
fun CardExpirationDateTextField(
    cardExpirationDate: String,
    maxLength: Int,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = cardExpirationDate,
        onValueChange = { text: String ->
            if (text.length <= maxLength) {
                onValueChange(text)
            }
        },
        label = { Text(stringResource(R.string.card_expiration_date)) },
        placeholder = { Text("MM / YY") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardExpirationDateTextFieldPreview() {
    CardExpirationDateTextField(
        cardExpirationDate = "0925",
        maxLength = 4,
        onValueChange = {},
        modifier = Modifier,
    )
}

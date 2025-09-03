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
import woowacourse.payments.domain.CardNumber

@Composable
fun CardNumberTextField(modifier: Modifier = Modifier) {
    var cardNumber: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardNumber,
        onValueChange = { text: String ->
            if (text.length <= CardNumber.CARD_NUMBER_LENGTH) cardNumber = text
        },
        label = { Text(stringResource(R.string.card_number)) },
        placeholder = { Text("0000 - 0000 - 0000 - 0000") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun CardNumberTextFieldPreview() {
    CardNumberTextField()
}

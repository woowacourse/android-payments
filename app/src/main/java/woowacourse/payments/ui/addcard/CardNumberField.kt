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
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.util.CardNumberVisualTransformation

@Composable
fun CardNumberField(
    cardNumber: CardNumberUiModel,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = cardNumber.toString(),
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        placeholder = { Text(stringResource(R.string.add_card_card_number_placeholder_text)) },
        label = { Text(stringResource(R.string.add_card_card_number_label_text)) },
        supportingText = {
            Text(" ")
        },
        visualTransformation = CardNumberVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberFieldPreview() {
    CardNumberField(
        cardNumber = CardNumberUiModel("0000", "0000", "0000", "0000"),
    )
}

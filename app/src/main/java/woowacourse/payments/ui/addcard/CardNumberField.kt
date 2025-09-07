package woowacourse.payments.ui.addcard

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import woowacourse.payments.R
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.ui.util.CardNumberVisualTransformation

@Composable
fun CardNumberField(
    cardNumber: CardNumber,
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

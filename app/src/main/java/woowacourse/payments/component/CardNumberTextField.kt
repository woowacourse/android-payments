package woowacourse.payments.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.core.CardNumberVisualTransformation
import woowacourse.payments.ui.theme.GrayAA

@Composable
fun CardNumberTextField(
    maxLength: Int,
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        CardInputEditText(
            cardNumber = cardNumber,
            onCardNumberChange = onCardNumberChange,
            maxLength = maxLength,
            modifier = Modifier
                .padding(top = 14.dp)
                .fillMaxWidth()
        )
        TextFieldLabel(text = stringResource(R.string.card_number))
    }
}

@Composable
fun CardInputEditText(
    maxLength: Int,
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardVisualTransformation = CardNumberVisualTransformation(maxLength)

    OutlinedTextField(
        value = cardNumber,
        onValueChange = { newText ->
            if (newText.length <= maxLength) {
                onCardNumberChange(newText)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        visualTransformation = cardVisualTransformation,
        placeholder = {
            Text(
                color = GrayAA,
                text = stringResource(R.string.credit_card_place_hodler)
            )
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun CardNumberTextFieldPreview() {
    CardNumberTextField(
        cardNumber = "",
        onCardNumberChange = {},
        maxLength = 16
    )
}

package woowacourse.payments.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardNumberInput(
    cardNumber: CardNumber?,
    onCardNumberChange: (CardNumber?) -> Unit,
    modifier: Modifier = Modifier,
    showValidationError: Boolean = false,
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            val filteredText = newText.filter { it.isDigit() }.take(16)
            text = filteredText
            onCardNumberChange(CardNumber(filteredText))
        },
        visualTransformation = CardNumberVisualTransformation(groupSize = 4, delimiter = " - "),
        modifier = modifier,
        label = { Text(text = stringResource(R.string.card_number_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_placeholder),
                color = Color.LightGray,
            )
        },
        isError = showValidationError && (cardNumber?.isValid != true),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@Composable
@Preview(showBackground = true)
fun CardNumberInputPreview() {
    AndroidpaymentsTheme {
        CardNumberInput(
            cardNumber = CardNumber(""),
            onCardNumberChange = { },
        )
    }
}

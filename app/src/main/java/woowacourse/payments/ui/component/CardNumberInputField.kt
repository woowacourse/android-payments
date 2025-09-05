package woowacourse.payments.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardNumberInputField(
    cardNumber: CardNumber?,
    onCardNumberChange: (CardNumber?) -> Unit,
    modifier: Modifier = Modifier,
    showValidationError: Boolean = false,
) {
    val transformation =
        remember { CardNumberVisualTransformation(groupSize = 4, delimiter = " - ") }

    OutlinedTextField(
        value = cardNumber?.value ?: "",
        onValueChange = { newText ->
            val filteredText = newText.filter { it.isDigit() }.take(16)
            val newCardNumber = CardNumber(filteredText)
            onCardNumberChange(newCardNumber)
        },
        modifier = modifier,
        label = { Text(text = stringResource(R.string.card_number_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_placeholder),
                color = Color.LightGray,
            )
        },
        isError = showValidationError && (cardNumber?.isValid != true),
        visualTransformation = transformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@Composable
@Preview(showBackground = true)
fun CardNumberInputPreview() {
    AndroidpaymentsTheme {
        CardNumberInputField(
            cardNumber = CardNumber(""),
            onCardNumberChange = { },
        )
    }
}

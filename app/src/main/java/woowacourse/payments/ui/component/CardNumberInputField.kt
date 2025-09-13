package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.ui.screen.addCard.AddCardError
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardNumberInputField(
    modifier: Modifier = Modifier,
    cardNumber: CardNumber? = null,
    onCardNumberChange: (CardNumber) -> Unit,
    error: AddCardError? = null,
) {
    val transformation =
        remember { CardNumberVisualTransformation(groupSize = 4, delimiter = " - ") }

    Column {
        OutlinedTextField(
            value = cardNumber?.value.orEmpty(),
            onValueChange = { newText ->
                val filteredText = newText.filter { it.isDigit() }.take(16)
                onCardNumberChange(CardNumber(filteredText))
            },
            modifier =
                modifier.semantics {
                    this.contentDescription = "Card Number Input Field"
                },
            label = { Text(text = stringResource(R.string.card_number_label)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.card_number_placeholder),
                    color = Color.LightGray,
                )
            },
            isError = error != null,
            visualTransformation = transformation,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        error?.let {
            Text(
                text = stringResource(R.string.card_number_invalid),
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
    }
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

@Composable
@Preview(showBackground = true)
fun CardNumberInputErrorPreview() {
    AndroidpaymentsTheme {
        CardNumberInputField(
            cardNumber = CardNumber(""),
            onCardNumberChange = { },
            error = AddCardError.CARD_NUMBER_INVALID,
        )
    }
}

package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.screen.addCard.AddCardError
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardNumberInputField(
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    error: AddCardError? = null,
) {
    val transformation =
        remember { CardNumberVisualTransformation(groupSize = 4, delimiter = " - ") }
    val context = LocalContext.current

    OutlinedTextField(
        value = cardNumber,
        onValueChange = { newText ->
            val filteredText = newText.filter { it.isDigit() }.take(16)
            onCardNumberChange(filteredText)
        },
        modifier =
            modifier.semantics {
                contentDescription = context.getString(R.string.card_number_content_description)
            },
        label = { Text(text = stringResource(R.string.card_number_label)) },
        placeholder = { Text(text = stringResource(R.string.card_number_placeholder)) },
        supportingText = {
            error?.let {
                Text(
                    text = stringResource(error.messageRes),
                    modifier =
                        Modifier
                            .padding(top = 4.dp)
                            .semantics {
                                contentDescription =
                                    context.getString(R.string.card_number_error_content_description)
                            },
                )
            }
        },
        isError = error != null,
        visualTransformation = transformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@Composable
@Preview(showBackground = true)
fun CardNumberInputPreview() {
    AndroidpaymentsTheme {
        CardNumberInputField(
            cardNumber = "",
            onCardNumberChange = { },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardNumberInputErrorPreview() {
    AndroidpaymentsTheme {
        CardNumberInputField(
            cardNumber = "",
            onCardNumberChange = { },
            error = AddCardError.CARD_NUMBER_INVALID,
        )
    }
}

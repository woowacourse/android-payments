package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.model.CardNumber.Companion.CARD_NUMBER_LENGTH
import woowacourse.payments.ui.text.CardNumberFormatter.CARD_NUMBER_CHUNK_SIZE
import woowacourse.payments.ui.text.CardNumberFormatter.CARD_NUMBER_SEPARATOR
import woowacourse.payments.ui.text.SeparatedVisualTransformation
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Grey40

@Composable
fun CardNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
) {
    val cardNumberVisualTransformation =
        remember {
            SeparatedVisualTransformation(
                groupSize = CARD_NUMBER_CHUNK_SIZE,
                separator = CARD_NUMBER_SEPARATOR,
            )
        }

    OutlinedTextField(
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        modifier = modifier,
        value = value,
        onValueChange = {
            onValueChange(it.filter(Char::isDigit).take(CARD_NUMBER_LENGTH))
        },
        visualTransformation = cardNumberVisualTransformation,
        label = { Text(stringResource(R.string.card_number_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_placeholder),
                color = Grey40,
            )
        },
        singleLine = true,
        isError = isError,
        supportingText = {
            if (isError && errorMessage != null) {
                Text(text = errorMessage)
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberFieldPreview() {
    AndroidpaymentsTheme {
        CardNumberField(
            value = "1234567890123456",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

package woowacourse.payments.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import java.lang.Character.isDigit

@Composable
fun CardNumberTextField(modifier: Modifier = Modifier) {
    var cardNumber by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardNumber,
        onValueChange = { newValue: String ->
            val newNumbers = newValue.filter(::isDigit)
            cardNumber = newNumbers.take(CARD_NUMBER_LENGTH_MAX)
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.card_number_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_placeholder),
                color = Color.Gray,
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = CardNumberVisualTransformation(maxInputLength = CARD_NUMBER_LENGTH_MAX)
    )
}

@Preview
@Composable
private fun CardNumberTextFieldPreview() {
    CardNumberTextField(modifier = Modifier.fillMaxWidth())
}

private const val CARD_NUMBER_LENGTH_MAX: Int = 16

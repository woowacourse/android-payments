package woowacourse.payments.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.core.CardNumberVisualTransformation
import woowacourse.payments.ui.theme.Black49

@Composable
fun CardNumberTextField(
    maxLength: Int,
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    onComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cardVisualTransformation = CardNumberVisualTransformation(maxLength)

    OutlinedTextField(
        value = cardNumber,
        onValueChange = { newText ->
            if (newText.length <= maxLength && newText.all { it.isDigit() }) {
                onCardNumberChange(newText)
            }
            if (newText.length == maxLength) {
                onComplete()
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        visualTransformation = cardVisualTransformation,
        placeholder = {
            TextFieldPlaceHolder(textResourceId = R.string.credit_card_place_holder)
        },
        label = {
            Text(
                text = stringResource(R.string.card_number),
                color = Black49
            )
        },
        singleLine = true,
        modifier = modifier
            .padding(top = 14.dp)
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberTextFieldPreview() {
    CardNumberTextField(
        cardNumber = "",
        onCardNumberChange = {},
        onComplete = {},
        maxLength = 16
    )
}

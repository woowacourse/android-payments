package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.screen.cardAddition.CardNumberVisualTransformation
import java.lang.Character.isDigit

private const val CARD_NUMBER_LENGTH_MAX: Int = 16

@Composable
fun CardNumberTextField(
    value: String,
    onCardNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onComplete: () -> Unit = {},
    onKeyboardActionClick: (KeyboardActionScope) -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue: String ->
            val cardNumber = newValue.filter(::isDigit).take(CARD_NUMBER_LENGTH_MAX)
            onCardNumberChange(cardNumber)
            if (newValue.length == CARD_NUMBER_LENGTH_MAX) onComplete()
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
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = onKeyboardActionClick),
        visualTransformation = CardNumberVisualTransformation(maxInputLength = CARD_NUMBER_LENGTH_MAX),
    )
}

@Preview
@Composable
private fun CardNumberTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    CardNumberTextField(
        value = text,
        onCardNumberChange = { text = it },
        modifier = Modifier.fillMaxWidth(),
    )
}

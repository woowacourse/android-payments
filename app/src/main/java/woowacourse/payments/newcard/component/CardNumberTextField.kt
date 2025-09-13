package woowacourse.payments.newcard.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import woowacourse.payments.R
import woowacourse.payments.newcard.transformation.cardNumberVisualTransformation

@Composable
fun CardNumberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { value: String ->
            if (isValidInput(value)) {
                onValueChange(value)
            }
        },
        label = { Text(stringResource(R.string.card_number)) },
        placeholder = {
            Text(
                text = "0000-0000-0000-0000",
                color = Color.Gray,
            )
        },
        visualTransformation = cardNumberVisualTransformation,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
        modifier = modifier,
    )
}

private fun isValidInput(numbers: String): Boolean = numbers.all { it.isDigit() } && numbers.length <= 16

package woowacourse.payments.newcard.component

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.newcard.transformation.cardNumberVisualTransformation

@Preview
@Composable
fun CardNumberTextField(modifier: Modifier = Modifier) {
    var cardNumber: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardNumber,
        onValueChange = { value: String ->
            if (isValidInput(value)) {
                cardNumber = value
            }
        },
        label = { Text("카드 번호") },
        placeholder = {
            Text(
                text = "0000-0000-0000-0000",
                color = Color.Gray,
            )
        },
        visualTransformation = cardNumberVisualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier,
    )
}

private fun isValidInput(numbers: String): Boolean = numbers.all { it.isDigit() } && numbers.length <= 16

package woowacourse.payments.presentation.newcard.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.presentation.newcard.transformation.cardNumberVisualTransformation

@Preview
@Composable
fun CardNumberTextField(modifier: Modifier = Modifier) {
    var cardNumber: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardNumber,
        onValueChange = { value: String ->
            runCatching {
                val newCardNumber = CardNumber(value)
                cardNumber = newCardNumber.numbers
            }
        },
        label = { Text("카드 번호") },
        placeholder = { Text("0000-0000-0000-0000") },
        visualTransformation = cardNumberVisualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier
    )
}

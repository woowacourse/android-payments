package woowacourse.payments.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CardNumberTextField(modifier: Modifier = Modifier) {
    var cardNumber by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardNumber,
        onValueChange = { newValue: String ->
            cardNumber = newValue
        },
        modifier = modifier,
        label = {
            Text(text = "카드 번호")
        },
        placeholder = {
            Text(
                text = "0000 - 0000 - 0000 - 0000",
                color = Color.Gray,
            )
        },
    )
}

@Preview
@Composable
private fun CardNumberTextFieldPreview() {
    CardNumberTextField(modifier = Modifier.fillMaxWidth())
}
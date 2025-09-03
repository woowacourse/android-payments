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

@Composable
fun CardNumberTextField(modifier: Modifier = Modifier) {
    var cardNumber by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardNumber,
        onValueChange = {
            cardNumber = it
        },
        modifier = modifier.fillMaxWidth(),
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
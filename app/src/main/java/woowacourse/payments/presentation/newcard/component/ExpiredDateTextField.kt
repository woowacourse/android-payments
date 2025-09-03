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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import woowacourse.payments.presentation.newcard.transformation.expiredDateVisualTransformation

@Composable
fun ExpiredDateTextField(modifier: Modifier = Modifier) {
    var expiredDate: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = expiredDate,
        onValueChange = { value: String ->
            if (isValidInput(value)) {
                expiredDate = value
            }
        },
        label = { Text("만료일") },
        placeholder = {
            Text(
                text = "MM/YY",
                color = Color.Gray
            )
        },
        visualTransformation = expiredDateVisualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier
    )
}

private fun isValidInput(date: String): Boolean {
    return date.all { it.isDigit() } && date.length <= 4
}

package woowacourse.payments.ui

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
fun ExpiredDateTextField(modifier: Modifier = Modifier) {
    var expiredDate by remember { mutableStateOf("") }

    OutlinedTextField(
        value = expiredDate,
        onValueChange = { newValue: String ->
            val newDate = newValue.filter { it.isDigit() }
            if (newDate.length >= 3) {
                val month = newDate.substring(0, 2)
                val year = newDate.substring(2, newDate.length.coerceAtMost(4))
                expiredDate = "$month/$year"
            } else {
                expiredDate = newDate
            }
        },
        modifier = modifier,
        label = { Text(text = "만료일") },
        placeholder = { Text(text = "MM/YY", color = Color.Gray) },
    )
}

@Preview
@Composable
private fun ExpiredDateTextFieldPreview() {
    ExpiredDateTextField()
}
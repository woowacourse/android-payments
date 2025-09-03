package woowacourse.payments.presentation.newcard.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.presentation.newcard.transformation.expiredDateVisualTransformation

@Composable
fun ExpiredDateTextField(modifier: Modifier = Modifier) {
    var expiredDate: String by remember { mutableStateOf("") }
    var isError: Boolean by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = expiredDate,
        onValueChange = { value: String ->
            if (isValidInput(value)) {
                expiredDate = value
                isError = value.length == 4 && !isValidExpiredDate(value)
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
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    text = "유효하지 않은 만료일자 형식입니다.",
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        trailingIcon = {
            if (isError) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "error",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        },
        modifier = modifier
    )
}

private fun isValidInput(date: String): Boolean {
    return date.all { it.isDigit() } && date.length <= 4
}

private fun isValidExpiredDate(date: String): Boolean {
    return try {
        val month = date.substring(0, 2).toInt()
        val year = date.substring(2, 4).toInt()
        val result = ExpiredDate.of(month, year) != null
        result
    } catch (e: NumberFormatException) {
        false
    }
}

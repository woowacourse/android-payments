package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import woowacourse.payments.domain.Expired

@Composable
fun ExpiredInput(
    expired: Expired?,
    onExpiredChange: (Expired?) -> Unit,
    modifier: Modifier = Modifier,
    showValidationError: Boolean = false,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                val digits = newValue.text.filter { it.isDigit() }.take(4)
                val formatted = digits.chunked(2).joinToString("/")
                val cursorPosition = formatted.length

                textFieldValue =
                    TextFieldValue(
                        text = formatted,
                        selection = TextRange(cursorPosition),
                    )
                onExpiredChange(Expired.create(formatted))
            },
            modifier = Modifier.fillMaxWidth(0.5f),
            label = { Text(text = "만료일") },
            placeholder = { Text(text = "MM / YY", color = Color.LightGray) },
            isError = showValidationError && (expired?.isValid != true),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

package woowacourse.payments.ui

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import java.lang.Character.isDigit

@Composable
fun ExpiredDateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(text = stringResource(R.string.expired_date_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expired_date_placeholder),
                color = Color.Gray,
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = ExpiredDateVisualTransformation(maxInputLength = maxLength),
    )
}

@Preview
@Composable
private fun ExpiredDateTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    ExpiredDateTextField(value = text, onValueChange = { text = it }, maxLength = 4)
}

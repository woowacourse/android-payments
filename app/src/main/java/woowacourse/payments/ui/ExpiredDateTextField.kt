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
fun ExpiredDateTextField(modifier: Modifier = Modifier) {
    var expiredDate by remember { mutableStateOf("") }

    OutlinedTextField(
        value = expiredDate,
        onValueChange = { newValue: String ->
            val newDate = newValue.filter(::isDigit)
            expiredDate = newDate.take(EXPIRED_DATE_LENGTH_MAX)
        },
        modifier = modifier,
        label = { Text(text = stringResource(R.string.expired_date_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expired_date_placeholder),
                color = Color.Gray
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = ExpiredDateVisualTransformation(maxInputLength = EXPIRED_DATE_LENGTH_MAX),
    )
}

@Preview
@Composable
private fun ExpiredDateTextFieldPreview() {
    ExpiredDateTextField()
}

private const val EXPIRED_DATE_LENGTH_MAX: Int = 4

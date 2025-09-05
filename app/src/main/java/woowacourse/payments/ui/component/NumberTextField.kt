package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Gray100
import woowacourse.payments.ui.theme.Gray200

@Composable
fun NumberTextField(
    modifier: Modifier = Modifier,
    label: Int,
    placeholder: Int,
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val textFieldValue =
        remember(value) {
            TextFieldValue(text = value, selection = TextRange(value.length))
        }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { input ->
            val digitsOnly = input.text.filter { it.isDigit() }.take(maxLength)
            onValueChange(digitsOnly)
        },
        label = { Text(stringResource(label), color = Gray200) },
        placeholder = { Text(stringResource(placeholder), color = Gray100) },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        modifier =
            modifier
                .padding(bottom = 30.dp)
                .padding(horizontal = 24.dp),
    )
}

@Preview(showBackground = true)
@Composable
private fun NumberTextFieldPreview() {
    NumberTextField(
        label = R.string.label_card_number,
        placeholder = R.string.placeholder_card_number,
        value = "2001 - 0928 - 1999 - 0511",
        onValueChange = {},
        maxLength = 16,
    )
}

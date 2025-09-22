package woowacourse.payments.ui.addcard.textfields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.format.ExpirationDateFormat
import woowacourse.payments.ui.theme.Gray

@Composable
fun ExpirationDateTextField(
    expirationDate: String,
    isError: Boolean,
    onValueChange: (newValue: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(0.5F),
        value = expirationDate,
        onValueChange = onValueChange,
        singleLine = true,
        visualTransformation = ExpirationDateFormat.visualTransformation,
        label = { Text(stringResource(R.string.expiration_date_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expiration_date_placeholder),
                color = Gray,
            )
        },
        supportingText = {
            Box(Modifier.height(20.dp)) {
                if (isError) Text(stringResource(R.string.expiration_date_error_message))
            }
        },
        isError = isError,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
    )
}

@Preview(showBackground = true, name = "만료일 입력란 (일반)")
@Composable
private fun ExpirationDateTextFieldPreview() {
    ExpirationDateTextField(
        expirationDate = "1234",
        isError = false,
        onValueChange = {},
    )
}

@Preview(showBackground = true, name = "만료일 입력란 (오류)")
@Composable
private fun ExpirationDateTextFieldWithErrorPreview() {
    ExpirationDateTextField(
        expirationDate = "1234",
        isError = true,
        onValueChange = {},
    )
}

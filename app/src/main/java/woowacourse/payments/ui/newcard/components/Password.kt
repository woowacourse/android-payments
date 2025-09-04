package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R

@Composable
fun Password(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    maxLength: Int,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        label = { Text(text = label) },
        onValueChange = { newValue ->
            if (newValue.length <= maxLength) onValueChange(newValue)
        },
        placeholder = { Text(placeholder) },
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordPreview() {
    Password(
        modifier =
            Modifier
                .padding(start = 24.dp, top = 30.dp),
        value = "0611",
        onValueChange = {},
        label = stringResource(R.string.main_password_label),
        placeholder = stringResource(R.string.main_password_placeholder),
        maxLength = 4,
    )
}

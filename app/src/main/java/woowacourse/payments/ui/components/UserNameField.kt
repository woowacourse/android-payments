package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Grey40

@Composable
fun UserNameField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { input ->
            if (input.length <= USER_NAME_MAX_LENGTH) onValueChange(input)
        },
        label = { Text(stringResource(R.string.user_name_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.user_name_placeholder),
                color = Grey40,
            )
        },
        supportingText = {
            Text(
                text =
                    stringResource(
                        R.string.user_name_counter,
                        value.length,
                        USER_NAME_MAX_LENGTH,
                    ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun UserNameFieldPreview() {
    AndroidpaymentsTheme {
        UserNameField(
            value = "조이",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

private const val USER_NAME_MAX_LENGTH = 30

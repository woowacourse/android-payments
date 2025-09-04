package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R

@Composable
fun Password(
    modifier: Modifier,
    label: String,
    placeholder: String,
    maxLength: Int,
) {
    var text: String by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        label = { Text(text = label) },
        onValueChange = { newValue ->
            if (newValue.length <= maxLength) text = newValue
        },
        placeholder = { Text(placeholder) },
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordPreview() {
    Password(
        modifier =
            Modifier
                .padding(start = 24.dp, top = 30.dp),
        label = stringResource(R.string.main_password_label),
        placeholder = stringResource(R.string.main_password_placeholder),
        maxLength = 4,
    )
}

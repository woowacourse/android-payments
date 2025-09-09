package woowacourse.payments.ui.features.addcard.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.PaymentCard.Companion.MAX_LENGTH_PASSWORD
import woowacourse.payments.ui.components.AppTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardPasswordField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }
    val isIncomplete = value.isNotEmpty() && value.length < MAX_LENGTH_PASSWORD
    val showError = isIncomplete && !isFocused

    val visualTransformation = remember { PasswordVisualTransformation() }
    AppTextField(
        value = value,
        onValueChange = { newValue ->
            val filteredValue = newValue.filter { it in '0'..'9' }.take(MAX_LENGTH_PASSWORD)
            onValueChange(filteredValue)
        },
        modifier =
            modifier.onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        labelText = stringResource(R.string.add_card_password_field_title),
        placeholderText = stringResource(R.string.add_card_password_field_hint),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isError = showError,
        supportingText = {
            if (showError) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.add_card_password_incomplete_error_message),
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
        visualTransformation = visualTransformation,
    )
}

@Preview(showBackground = true)
@Composable
fun CardPasswordFieldPreview() {
    var text by remember { mutableStateOf("") }
    AndroidpaymentsTheme(dynamicColor = false) {
        CardPasswordField(
            value = text,
            onValueChange = { text = it },
        )
    }
}

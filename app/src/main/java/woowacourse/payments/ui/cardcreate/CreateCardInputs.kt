package woowacourse.payments.ui.cardcreate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import woowacourse.payments.ui.components.PaymentsOutlinedTextField

@Composable
fun CreateCardNumbersInput(
    value: String,
    labelText: String,
    placeholderText: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    PaymentsOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholderText,
        visualTransformation = visualTransformation,
        label = labelText,
        modifier = modifier,
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun CreateCardExpiryDateInput(
    value: String,
    labelText: String,
    errorMessage: String?,
    placeholderText: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation,
    onValidate: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }
    PaymentsOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholderText,
        isError = errorMessage != null,
        supportingText = {
            if (errorMessage != null) Text(errorMessage)
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onDone = { onValidate(value) }),
        label = labelText,
        modifier = modifier.onFocusChanged {
            val now = it.isFocused
            if (isFocused && !now) onValidate(value)
            isFocused = now
        },
    )
}

@Composable
fun CreateCardOwnerNameInput(
    value: String,
    maxLength: Int,
    labelText: String,
    placeholderText: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    PaymentsOutlinedTextField(
        value = value,
        label = labelText,
        placeholder = placeholderText,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        supportingText = {
            Box(
                modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) { Text("${value.length}/${maxLength}") }
        }
    )
}


@Composable
fun CreateCardPasswordInput(
    value: String,
    labelText: String,
    placeholderText: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    PaymentsOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholderText,
        keyboardOptions = keyboardOptions,
        visualTransformation = PasswordVisualTransformation(),
        label = labelText,
        modifier = modifier,
    )
}
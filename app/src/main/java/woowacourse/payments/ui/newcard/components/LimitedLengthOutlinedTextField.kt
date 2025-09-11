package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun LimitedLengthOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    inputFilter: (String) -> String = { it },
) {
    OutlinedTextField(
        value = value,
        onValueChange = { text: String ->
            val filteredText = inputFilter(text)
            onValueChange(filteredText.take(maxLength))
        },
        label = label,
        placeholder = placeholder,
        supportingText = supportingText,
        isError = isError,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        modifier = modifier,
    )
}

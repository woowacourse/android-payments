package woowacourse.payments.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PaymentTextField(
    text: String,
    onValueChanged: (newText: String) -> Unit,
    label: String,
    hint: String,
    maxLength: Int,
    modifier: Modifier = Modifier,
    supportingText: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            if (newText.length <= maxLength) {
                onValueChanged.invoke(newText)
            }
        },
        label = { Text(text = label) },
        placeholder = { Text(text = hint) },
        enabled = true,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        supportingText = supportingText,
        visualTransformation = visualTransformation,
        modifier = modifier,
    )
}

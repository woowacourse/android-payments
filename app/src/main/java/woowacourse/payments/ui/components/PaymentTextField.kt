package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign

@Composable
fun PaymentTextField(
    text: String,
    onValueChanged: (newText: String) -> Unit,
    label: String,
    hint: String,
    maxLength: Int,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    hasSupportingText: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier,
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
        supportingText = {
            if (hasSupportingText) {
                Text(
                    text = "${text.length}/30",
                    textAlign = TextAlign.End,
                    modifier = modifier.fillMaxWidth()
                )
            } else Text(text = "")
        },
        visualTransformation = visualTransformation,
        modifier = modifier,
    )
}

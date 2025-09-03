package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign

@Composable
fun PaymentTextField(
    label: String,
    hint: String,
    maxLength: Int,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isTextHide: Boolean = false,
    hasSupportingText: Boolean = false,
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            if (newText.length <= maxLength) {
                text = newText
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
        visualTransformation = if (isTextHide) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = modifier,
    )
}

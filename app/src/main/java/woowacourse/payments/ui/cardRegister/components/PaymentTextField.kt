package woowacourse.payments.ui.cardRegister.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentTextField(
    text: String,
    onValueChanged: (newText: String) -> Unit,
    label: String,
    placeholder: String,
    maxLength: Int,
    modifier: Modifier = Modifier,
    onlyDigits: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { input ->
            var filtered = input
            if (onlyDigits) filtered = filtered.filter { it.isDigit() }

            if (filtered.length > maxLength) filtered = filtered.take(maxLength)

            if (filtered.length <= maxLength) {
                onValueChanged.invoke(filtered)
            }
        },
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder, color = Color(0xFFAAAAAA)) },
        enabled = true,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        supportingText = supportingText,
        visualTransformation = visualTransformation,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun PaymentTextFieldPreview() {
    AndroidpaymentsTheme {
        PaymentTextField(
            label = "닉네임",
            placeholder = "뭉치즈",
            maxLength = 4,
            text = "뭉치즈",
            supportingText = {
                Text(
                    text = "3/30",
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            onValueChanged = { "뭉치즈에요 " },
        )
    }
}

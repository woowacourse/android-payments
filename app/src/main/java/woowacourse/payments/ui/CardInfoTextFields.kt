package woowacourse.payments.ui

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation

@Suppress("ktlint:standard:function-naming")
@Composable
fun CardInfoTextFields(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeholder: String = "",
    supportingText: @Composable () -> Unit = {},
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        label = { Text(label) },
        placeholder = { Text(text = placeholder, color = Color(0xFFAAAAAA)) },
        singleLine = true,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        onValueChange = onValueChange,
    )
}

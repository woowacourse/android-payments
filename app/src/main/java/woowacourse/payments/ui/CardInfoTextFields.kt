package woowacourse.payments.ui

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

@Suppress("ktlint:standard:function-naming")
@Composable
fun CardInfoTextFields(
    modifier: Modifier = Modifier,
    value: String = "",
    label: @Composable () -> Unit,
    placeholder: @Composable () -> Unit,
    supportingText: @Composable () -> Unit = {},
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        label = label,
        placeholder = placeholder,
        singleLine = true,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        onValueChange = onValueChange,
    )
}

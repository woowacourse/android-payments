package woowacourse.payments.ui.newcard.textfields

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import woowacourse.payments.ui.theme.Gray

@Suppress("ktlint:standard:function-naming")
@Composable
fun CardInfoTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeholder: String = "",
    supportingText: @Composable () -> Unit = {},
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        label = { Text(label) },
        placeholder = { Text(text = placeholder, color = Gray) },
        singleLine = true,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onValueChange = onValueChange,
    )
}

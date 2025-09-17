package woowacourse.payments.ui.addcard.textfields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Gray

@Composable
fun PasscodeTextField(
    passcode: MutableState<String>,
    isError: MutableState<Boolean>,
    onValueChange: (newValue: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(0.5F),
        value = passcode.value,
        onValueChange = onValueChange,
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        label = { Text(stringResource(R.string.passcode_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.passcode_placeholder),
                color = Gray,
            )
        },
        supportingText = {
            Box(Modifier.height(20.dp)) {
                if (isError.value) Text(stringResource(R.string.passcode_error_message))
            }
        },
        isError = isError.value,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done,
            ),
    )
}

@Preview(showBackground = true, name = "비밀번호 입력란 (일반)")
@Composable
private fun PasscodeTextFieldPreview() {
    PasscodeTextField(
        passcode = remember { mutableStateOf("0000") },
        isError = remember { mutableStateOf(true) },
        onValueChange = {},
    )
}

@Preview(showBackground = true, name = "비밀번호 입력란 (오류)")
@Composable
private fun PasscodeTextFieldWithErrorPreview() {
    PasscodeTextField(
        passcode = remember { mutableStateOf("00") },
        isError = remember { mutableStateOf(true) },
        onValueChange = {},
    )
}

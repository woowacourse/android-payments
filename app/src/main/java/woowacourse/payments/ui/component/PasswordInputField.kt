package woowacourse.payments.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PasswordInputField(
    password: Password?,
    onPasswordChange: (Password?) -> Unit,
    modifier: Modifier = Modifier,
    showValidationError: Boolean = false,
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val passwordTransformation = remember { PasswordVisualTransformation() }

    OutlinedTextField(
        value = password?.value ?: "",
        onValueChange = { newText ->
            val filteredText = newText.filter { it.isDigit() }.take(4)
            onPasswordChange(if (filteredText.isEmpty()) null else Password(filteredText))
        },
        modifier =
            modifier.semantics {
                this.contentDescription = "Password Input Field"
            },
        label = { Text(text = stringResource(R.string.password_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.password_placeholder),
                color = Color.LightGray,
            )
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else passwordTransformation,
        trailingIcon = {
            if (isFocused) {
                val painter =
                    if (passwordVisible) {
                        painterResource(id = R.drawable.ic_visible)
                    } else {
                        painterResource(id = R.drawable.ic_not_visible)
                    }

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painter,
                        contentDescription = if (passwordVisible) "숨기기" else "보이기",
                    )
                }
            }
        },
        interactionSource = interactionSource,
        isError = showValidationError && (password?.isValid != true),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@Composable
@Preview(showBackground = true)
fun PasswordInputPreview() {
    AndroidpaymentsTheme {
        PasswordInputField(
            password = null,
            onPasswordChange = { },
        )
    }
}

package woowacourse.payments.ui.addcard.textfields

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Gray

private const val CARDHOLDER_NAME_MAX_LENGTH = 30

@Suppress("ktlint:standard:function-naming")
@Composable
fun CardHolderNameTextField(text: MutableState<String>) {
    val focusManager = LocalFocusManager.current

    fun updateValue(newValue: String) {
        text.value = newValue.take(CARDHOLDER_NAME_MAX_LENGTH)
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text.value,
        onValueChange = { newValue: String -> updateValue(newValue) },
        singleLine = true,
        label = { Text(stringResource(R.string.cardholder_name_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.cardholder_name_placeholder),
                color = Gray,
            )
        },
        supportingText = {
            Text(
                text =
                    stringResource(
                        R.string.cardholder_name_entry_length,
                        text.value.length,
                        CARDHOLDER_NAME_MAX_LENGTH,
                    ),
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Next) }),
    )
}

@Suppress("ktlint:standard:function-naming")
@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun CardHolderNameTextFieldPreview() {
    val text: MutableState<String> = mutableStateOf("디랙")
    CardHolderNameTextField(text)
}

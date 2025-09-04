package woowacourse.payments.ui.newcard.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import woowacourse.payments.R
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.ui.theme.Gray

private const val CARD_NUMBER_REQUIRED_LENGTH = 16

@Suppress("ktlint:standard:function-naming")
@Composable
fun CardNumberTextField(
    text: MutableState<String>,
    isError: MutableState<Boolean>,
) {
    val focusManager = LocalFocusManager.current

    fun updateValue(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(CARD_NUMBER_REQUIRED_LENGTH)

        text.value = filteredValue
        isError.value = runCatching { CardNumber(text.value) }.isFailure

        if (!isError.value && filteredValue.length == CARD_NUMBER_REQUIRED_LENGTH) {
            focusManager.moveFocus(FocusDirection.Next)
        }
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text.value,
        onValueChange = { newValue: String -> updateValue(newValue) },
        singleLine = true,
        visualTransformation = CardNumberTransformation,
        label = { Text(stringResource(R.string.card_number_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_placeholder),
                color = Gray,
            )
        },
        supportingText = {
            Text(
                if (isError.value) {
                    stringResource(R.string.card_number_error_message)
                } else {
                    ""
                },
            )
        },
        isError = isError.value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Next) }),
    )
}

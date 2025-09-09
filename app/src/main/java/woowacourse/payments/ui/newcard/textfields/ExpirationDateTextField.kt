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
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.ui.formatter.ExpirationDateFormat
import woowacourse.payments.ui.theme.Gray
import java.time.YearMonth

@Suppress("ktlint:standard:function-naming")
@Composable
fun ExpirationDateTextField(
    text: MutableState<String>,
    isError: MutableState<Boolean>,
) {
    val focusManager = LocalFocusManager.current

    fun updateValue(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(ExpirationDateFormat.REQUIRED_LENGTH)
        text.value = filteredValue

        isError.value =
            runCatching {
                ExpirationDate(
                    YearMonth.parse(
                        filteredValue,
                        ExpirationDateFormat.formatPattern,
                    ),
                )
            }.isFailure

        if (!isError.value && filteredValue.length == ExpirationDateFormat.REQUIRED_LENGTH) {
            focusManager.moveFocus(FocusDirection.Next)
        }
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.5F),
        value = text.value,
        onValueChange = { newValue: String -> updateValue(newValue) },
        singleLine = true,
        visualTransformation = ExpirationDateFormat.visualTransformation,
        label = { Text(stringResource(R.string.expiration_date_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expiration_date_placeholder),
                color = Gray,
            )
        },
        supportingText = {
            Text(
                if (isError.value) {
                    stringResource(R.string.expiration_date_error_message)
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

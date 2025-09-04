package woowacourse.payments.ui.newcard.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import java.time.YearMonth
import java.time.format.DateTimeFormatter

private const val EXPIRATION_DATE_REQUIRED_LENGTH = 4

@Suppress("ktlint:standard:function-naming")
@Composable
fun ExpirationDateTextField(
    text: MutableState<String>,
    isError: MutableState<Boolean>,
    now: YearMonth,
) {
    val focusManager = LocalFocusManager.current

    CardInfoTextFields(
        modifier = Modifier.fillMaxWidth(0.5F),
        value = text.value,
        label = stringResource(R.string.expiration_date_label),
        placeholder = stringResource(R.string.expiration_date_placeholder),
        isError = isError.value,
        supportingText = {
            Text(
                if (isError.value) {
                    stringResource(R.string.expiration_date_error_message)
                } else {
                    ""
                },
            )
        },
        visualTransformation = ExpirationDateTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Next) }),
    ) { newValue: String ->
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(EXPIRATION_DATE_REQUIRED_LENGTH)
        text.value = filteredValue

        isError.value =
            runCatching {
                ExpirationDate(
                    YearMonth.parse(
                        filteredValue,
                        DateTimeFormatter.ofPattern("MMyy"),
                    ),
                    now,
                )
            }.isFailure

        if (!isError.value && filteredValue.length == EXPIRATION_DATE_REQUIRED_LENGTH) {
            focusManager.moveFocus(FocusDirection.Next)
        }
    }
}

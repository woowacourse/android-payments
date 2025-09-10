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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.ui.format.ExpirationDateFormat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.Gray
import java.time.YearMonth

@Composable
fun ExpirationDateTextField(
    card: MutableState<CardUiModel>,
    isError: MutableState<Boolean>,
) {
    val focusManager = LocalFocusManager.current

    fun updateValue(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(ExpirationDateFormat.REQUIRED_LENGTH)
        card.value = card.value.copy(expirationDate = filteredValue)

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
        value = card.value.expirationDate,
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
            Box(Modifier.height(20.dp)) {
                if (isError.value) Text(stringResource(R.string.expiration_date_error_message)) else null
            }
        },
        isError = isError.value,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
    )
}

@Preview(showBackground = true)
@Composable
fun ExpirationDateTextFieldPreview() {
    ExpirationDateTextField(
        card =
            remember {
                mutableStateOf(
                    CardUiModel(
                        "1234123412341234",
                        "1234",
                        "CREW",
                        "0000",
                    ),
                )
            },
        isError = remember { mutableStateOf(false) },
    )
}

@Preview(showBackground = true)
@Composable
fun ExpirationDateTextFieldWithErrorPreview() {
    ExpirationDateTextField(
        card =
            remember {
                mutableStateOf(
                    CardUiModel(
                        "1234123412341234",
                        "9999",
                        "CREW",
                        "0000",
                    ),
                )
            },
        isError = remember { mutableStateOf(true) },
    )
}

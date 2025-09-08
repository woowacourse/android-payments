package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.screen.cardAddition.ExpiredDateVisualTransformation
import java.lang.Character.isDigit

private const val EXPIRED_DATE_LENGTH_MAX: Int = 4

@Composable
fun ExpiredDateTextField(
    value: String,
    onDateChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    onComplete: () -> Unit = {},
    onKeyboardActionClick: (KeyboardActionScope) -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue: String ->
            val newDate = newValue.filter(::isDigit).take(EXPIRED_DATE_LENGTH_MAX)
            onDateChange(newDate)
            if (newDate.length == EXPIRED_DATE_LENGTH_MAX) onComplete()
        },
        modifier = modifier,
        label = { Text(text = stringResource(R.string.expired_date_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expired_date_placeholder),
                color = Color.Gray,
            )
        },
        supportingText = {
            if (errorMessage != null) {
                Text(text = errorMessage)
            }
        },
        isError = errorMessage != null,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
        keyboardActions = KeyboardActions(onNext = onKeyboardActionClick),
        visualTransformation = ExpiredDateVisualTransformation(maxInputLength = EXPIRED_DATE_LENGTH_MAX),
    )
}

@Preview
@Composable
private fun ExpiredDateTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    ExpiredDateTextField(value = text, onDateChange = { text = it })
}

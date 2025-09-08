package woowacourse.payments.cardaddition.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.util.GroupingVisualTransformation
import java.time.Month

@Composable
fun ExpiredDateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.expired_date_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.expired_date_placeholder),
                color = Color.Gray,
            )
        },
        supportingText = {
            if (value.isInvalidExpiredDate) {
                Text(
                    text = stringResource(R.string.text_field_invalid_format_message),
                    color = Color.Red,
                )
            }
        },
        isError = value.isInvalidExpiredDate,
        visualTransformation =
            GroupingVisualTransformation(
                EXPIRED_DATE_GROUP_SIZE,
                EXPIRED_DATE_DELIMITER,
            ),
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
    )
}

@Preview
@Composable
private fun ExpiredDateTextFieldPreview() {
    val (expiredDate: String, setExpiredDate: (String) -> Unit) = remember { mutableStateOf("") }

    ExpiredDateTextField(
        value = expiredDate,
        onValueChange = setExpiredDate,
    )
}

private val String.isInvalidExpiredDate: Boolean
    get() {
        if (isEmpty()) return false

        val month: Int = take(2).toInt()
        return length != EXPIRED_DATE_LENGTH || month !in Month.entries.map(Month::getValue)
    }

const val EXPIRED_DATE_LENGTH: Int = 4
private const val EXPIRED_DATE_GROUP_SIZE: Int = 2
private const val EXPIRED_DATE_DELIMITER: String = " / "

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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import woowacourse.payments.R
import woowacourse.payments.ui.GroupingVisualTransformation

@Composable
fun ExpiredDateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
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
            if (isError) {
                Text(
                    text = stringResource(R.string.text_field_invalid_format_message),
                    color = Color.Red,
                )
            }
        },
        isError = isError,
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
private fun ExpiredDateTextFieldPreview(
    @PreviewParameter(
        ExpiredDateTextFieldPreviewParameterProvider::class,
    ) isError: Boolean,
) {
    val (expiredDate: String, setExpiredDate: (String) -> Unit) = remember { mutableStateOf("") }

    ExpiredDateTextField(
        value = expiredDate,
        onValueChange = setExpiredDate,
        isError = isError,
    )
}

class ExpiredDateTextFieldPreviewParameterProvider : CollectionPreviewParameterProvider<Boolean>(listOf(false, true))

private const val EXPIRED_DATE_GROUP_SIZE: Int = 2
private const val EXPIRED_DATE_DELIMITER: String = " / "

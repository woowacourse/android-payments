package woowacourse.payments.cardaddition.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import woowacourse.payments.R
import woowacourse.payments.ui.GroupingVisualTransformation

@Composable
fun CardNumberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.testTag("CardNumberTextField"),
        label = {
            Text(text = stringResource(R.string.card_number_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_placeholder),
                color = Color.Gray,
            )
        },
        supportingText = {
            if (isError) {
                Text(
                    text = stringResource(R.string.text_field_invalid_format_message),
                    color = Color.Red,
                    modifier = Modifier.testTag("CardNumberTextFieldSupportingText"),
                )
            }
        },
        isError = isError,
        visualTransformation =
            GroupingVisualTransformation(
                CARD_NUMBER_GROUP_SIZE,
                CARD_NUMBER_SEPARATOR,
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
private fun CardNumberTextFieldPreview(
    @PreviewParameter(CardNumberTextFieldPreviewParameterProvider::class) isError: Boolean,
) {
    val (cardNumber: String, setCardNumber: (String) -> Unit) = remember { mutableStateOf("") }

    CardNumberTextField(
        value = cardNumber,
        onValueChange = setCardNumber,
        isError = isError,
        modifier = Modifier.fillMaxWidth(),
    )
}

private class CardNumberTextFieldPreviewParameterProvider : CollectionPreviewParameterProvider<Boolean>(listOf(false, true))

private const val CARD_NUMBER_GROUP_SIZE: Int = 4
private const val CARD_NUMBER_SEPARATOR: String = " - "

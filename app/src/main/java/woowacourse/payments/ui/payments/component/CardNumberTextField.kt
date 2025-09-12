package woowacourse.payments.ui.payments.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import woowacourse.payments.R
import woowacourse.payments.ui.common.GroupedVisualTransformation

private const val CARD_NUMBER_TEXT_FIELD_TEST_TAG = "CardNumberTextField"

@Composable
fun CardNumberTextField(
    modifier: Modifier = Modifier,
    cardNumber: String,
    maxLength: Int,
    onCardNumberChanged: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier.testTag(CARD_NUMBER_TEXT_FIELD_TEST_TAG),
        label = {
            Text(text = stringResource(R.string.card_number_text_field_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_text_field_placeholder),
                color = Color.Gray,
            )
        },
        value = cardNumber,
        onValueChange = { newValue ->
            if (newValue.length > maxLength) {
                return@OutlinedTextField onCardNumberChanged(newValue.take(maxLength))
            }
            if (newValue.isDigitsOnly().not()) return@OutlinedTextField
            onCardNumberChanged(newValue)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation =
            GroupedVisualTransformation(
                maxLength = maxLength,
                groupSize = 4,
                separator = " - ",
            ),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardNumberTextFieldPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        CardNumberTextField(cardNumber = "", maxLength = 16) {}
    }
}

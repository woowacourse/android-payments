package woowacourse.payments.ui.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

private const val CARD_NUMBER_LENGTH = 16
private const val CARD_NUMBER_GROUP_SIZE = 4
private const val CARD_NUMBER_SEPARATOR = " - "
private const val CARD_NUMBER_TEXT_FIELD_TEST_TAG = "CardNumberTextField"

@Composable
fun CardNumberTextField(
    cardNumber: String,
    onCardNumberChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val visualTransformation =
        remember { GroupedVisualTransformation(CARD_NUMBER_GROUP_SIZE, CARD_NUMBER_SEPARATOR) }

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
            if (newValue.length > CARD_NUMBER_LENGTH) {
                return@OutlinedTextField onCardNumberChanged(newValue.take(CARD_NUMBER_LENGTH))
            }
            if (newValue.isDigitsOnly().not()) return@OutlinedTextField
            onCardNumberChanged(newValue)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = visualTransformation,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardNumberTextFieldPreview() {
    Column(modifier = Modifier.padding(CARD_NUMBER_LENGTH.dp)) {
        CardNumberTextField(
            cardNumber = "",
            onCardNumberChanged = {},
        )
        CardNumberTextField(
            cardNumber = "1234123412341234",
            onCardNumberChanged = {},
        )
    }
}

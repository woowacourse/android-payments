package woowacourse.payments.ui.payments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun CardNumberTextField(
    cardNumber: String,
    onCardNumberChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
) {
    val visualTransformation =
        remember { GroupedVisualTransformation(CARD_NUMBER_GROUP_SIZE, CARD_NUMBER_SEPARATOR) }

    OutlinedTextField(
        label = { Text(text = stringResource(R.string.card_number_text_field_label)) },
        placeholder = { Text(text = stringResource(R.string.card_number_text_field_placeholder)) },
        value = cardNumber,
        onValueChange = { newValue ->
            val newCardNumber = newValue.take(CARD_NUMBER_LENGTH)
            if (newCardNumber.isDigitsOnly().not()) return@OutlinedTextField

            onCardNumberChanged(newCardNumber)
        },
        isError = errorMessage != null,
        supportingText = { errorMessage?.let { message -> Text(text = message) } },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = visualTransformation,
        colors =
            OutlinedTextFieldDefaults.colors(
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
            ),
        modifier = modifier,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardNumberTextFieldPreview() {
    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        CardNumberTextField(
            cardNumber = "",
            onCardNumberChanged = {},
        )
        CardNumberTextField(
            cardNumber = "1234123412341234",
            onCardNumberChanged = {},
        )
        CardNumberTextField(
            cardNumber = "1234123412341234",
            onCardNumberChanged = {},
            errorMessage = "유효하지 않은 카드번호입니다.",
        )
    }
}

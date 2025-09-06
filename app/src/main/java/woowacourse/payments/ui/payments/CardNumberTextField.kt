package woowacourse.payments.ui.payments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import woowacourse.payments.R
import woowacourse.payments.ui.common.CreditCardVisualTransformation

private const val CardNumberLength = 16

@Composable
fun CardNumberTextField(
    modifier: Modifier = Modifier,
    cardNumber: String,
    onCardNumberChanged: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier.semantics { contentDescription = "카드 번호" },
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
            if (newValue.length > CardNumberLength || !newValue.isDigitsOnly()) return@OutlinedTextField
            onCardNumberChanged(newValue)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = CreditCardVisualTransformation,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardNumberTextFieldPreview() {
    Box(modifier = Modifier.padding(CardNumberLength.dp)) {
        CardNumberTextField(cardNumber = "") {}
    }
}

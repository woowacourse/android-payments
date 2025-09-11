package woowacourse.payments.ui.component

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
import woowacourse.payments.R
import woowacourse.payments.ui.common.CreditCardVisualTransformation
import woowacourse.payments.ui.model.CardNumberUiModel

@Composable
fun CardNumberTextField(
    cardNumber: CardNumberUiModel,
    onCardNumberChanged: (CardNumberUiModel) -> Unit,
    modifier: Modifier = Modifier,
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
        value = cardNumber.value,
        onValueChange = { newValue ->
            val newCardNumber: CardNumberUiModel =
                runCatching { CardNumberUiModel(newValue) }.getOrNull() ?: return@OutlinedTextField
            if (!newCardNumber.isValid) return@OutlinedTextField
            onCardNumberChanged(newCardNumber)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = CreditCardVisualTransformation,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CardNumberTextFieldPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        CardNumberTextField(cardNumber = CardNumberUiModel(), onCardNumberChanged = {})
    }
}

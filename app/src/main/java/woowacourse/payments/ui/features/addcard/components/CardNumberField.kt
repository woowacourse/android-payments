package woowacourse.payments.ui.features.addcard.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.components.AppTextField
import woowacourse.payments.ui.features.addcard.CardUiState
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.CardNumberVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        labelText = stringResource(R.string.add_card_number_field_title),
        placeholderText = stringResource(R.string.add_card_number_field_hint),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = CardNumberVisualTransformation(),
    )
}

@Preview(showBackground = true)
@Composable
fun CardNumberFieldPreview() {
    var text by remember { mutableStateOf("") }
    AndroidpaymentsTheme(dynamicColor = false) {
        CardNumberField(
            value = text,
            onValueChange = {
                text = CardUiState().withCardNumber(it).cardNumber
            },
        )
    }
}

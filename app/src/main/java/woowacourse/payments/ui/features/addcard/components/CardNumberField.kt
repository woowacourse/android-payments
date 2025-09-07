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
import woowacourse.payments.domain.Card.Companion.MAX_LENGTH_CARD_NUMBER
import woowacourse.payments.ui.components.AppTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.CardNumberVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardNumberField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val visualTransformation = remember { CardNumberVisualTransformation() }

    AppTextField(
        value = value,
        onValueChange = { newValue ->
            val filteredValue = newValue.filter { it in '0'..'9' }.take(MAX_LENGTH_CARD_NUMBER)
            onValueChange(filteredValue)
        },
        modifier = modifier,
        labelText = stringResource(R.string.add_card_number_field_title),
        placeholderText = stringResource(R.string.add_card_number_field_hint),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = visualTransformation,
    )
}

@Preview(showBackground = true)
@Composable
fun CardNumberFieldPreview() {
    var text by remember { mutableStateOf("") }
    AndroidpaymentsTheme(dynamicColor = false) {
        CardNumberField(
            value = text,
            onValueChange = { text = it },
        )
    }
}
